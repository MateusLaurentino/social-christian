package com.mla.socialchristian.domain.interfaces.services;

import com.mla.socialchristian.domain.DTOs.inputmodels.AccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.AlterAccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.TokenInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.AccountUserOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.TokenOutputModel;
import java.util.Date;

public interface IAccountService {
     ResultViewModel<Boolean> createUser(AccountUserInputModel input);
     ResultViewModel<TokenOutputModel> generateToken(TokenInputModel input);
     ResultViewModel<AccountUserOutputModel> get(Integer id);
     ResultViewModel<Boolean> update(Integer id, AlterAccountUserInputModel input);
     ResultViewModel<Boolean> delete(Integer id, Date tokenExpiration);
}
