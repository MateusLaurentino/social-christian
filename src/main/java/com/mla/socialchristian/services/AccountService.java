package com.mla.socialchristian.services;

import com.mla.socialchristian.configurations.SecurityConfiguration;
import com.mla.socialchristian.domain.DTOs.inputmodels.AccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.AlterAccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.TokenInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.AccountUserOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.TokenOutputModel;
import com.mla.socialchristian.domain.entities.AccountUserEntity;
import com.mla.socialchristian.domain.interfaces.repository.IAccountRepository;
import com.mla.socialchristian.domain.interfaces.repository.IBlacklistTokenRepository;
import com.mla.socialchristian.domain.interfaces.repository.IPublishRepository;
import com.mla.socialchristian.domain.interfaces.services.IAccountService;
import com.mla.socialchristian.security.JWTCreator;
import com.mla.socialchristian.security.LoggedUserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IBlacklistTokenRepository blacklistTokenRepository;

    @Autowired
    private IPublishRepository publishRepository;

    @Override
    public ResultViewModel<Boolean> createUser(AccountUserInputModel input) {
        var result = new ResultViewModel<>(false);
        if (accountRepository.existsByEmail(input.email))
            return result.addErrors("Erro ao criar conta.");

        var newPass = passwordEncoder.encode(input.password);

        var accountEntity = accountRepository.saveAndFlush(new AccountUserEntity(input, newPass));
        return accountEntity.getId() > 0
                ? result.addResult(true)
                : result.addErrors("Erro ao criar conta.");
    }

    @Override
    public ResultViewModel<TokenOutputModel> generateToken(TokenInputModel input) {
        var result = new ResultViewModel<TokenOutputModel>();

        var user = accountRepository.findByEmail(input.email);
        if (user == null)
            return result.addErrors("Erro ao acessar conta.");

        if (!passwordEncoder.matches(input.password, user.getPassword()))
            return result.addErrors("Erro ao acessar conta.");

        var timeCurrent = System.currentTimeMillis();

        var loggedUser = new LoggedUserModel()
                .setId(user.getId())
                .setName(user.getName())
                .setDates(timeCurrent, SecurityConfiguration.EXPIRATION)
                .setRoles(List.of("ROLE_USER"));

        var output = new TokenOutputModel(loggedUser.getExpiration());
        try {
            output.Token = JWTCreator.create(SecurityConfiguration.KEY, loggedUser);
        } catch (Exception e) {
            return result.addErrors("Erro ao acessar conta.");
        }

        return result.addResult(output);
    }

    @Override
    public ResultViewModel<AccountUserOutputModel> get(Integer id) {
        var user = accountRepository.findById(id).orElseThrow();
        var output = new AccountUserOutputModel(user);
        return new ResultViewModel<>(output);
    }

    @Override
    public ResultViewModel<Boolean> update(Integer id, AlterAccountUserInputModel input) {
        var result = new ResultViewModel<>(false);

        var accountEntity = accountRepository.findById(id).orElseThrow();

        accountEntity.setName(input.name);
        accountEntity.setBirthday(input.birthday);

        accountRepository.saveAndFlush(accountEntity);

        return result.addResult(true);
    }

    @Override
    @Transactional
    public ResultViewModel<Boolean> delete(Integer id, Date tokenExpiration) {
        var result = new ResultViewModel<Boolean>();

        publishRepository.deleteByIdAccount(id);

        accountRepository.deleteById(id);
        accountRepository.flush();

        var token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var timeStampNow = System.currentTimeMillis();

        var updated = blacklistTokenRepository.update(token, tokenExpiration.getTime(), timeStampNow);
        if (updated == 0)
            blacklistTokenRepository.insert(token, tokenExpiration.getTime(), timeStampNow);

        result.addResult(true);

        return result;
    }
}
