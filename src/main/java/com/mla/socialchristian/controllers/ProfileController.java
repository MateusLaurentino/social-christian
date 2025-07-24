package com.mla.socialchristian.controllers;

import com.mla.socialchristian.controllers.base.ControllerBase;
import com.mla.socialchristian.domain.DTOs.inputmodels.AlterAccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.AccountUserOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.PublishDetailOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.interfaces.services.IAccountService;
import com.mla.socialchristian.domain.interfaces.services.IPublishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profile", produces = "application/json")
public class ProfileController extends ControllerBase {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPublishService publishService;

    @GetMapping()
    public ResponseEntity<ResultViewModel<AccountUserOutputModel>> getAccount() {
        return response(accountService.get(getUser().getId()));
    }

    @PutMapping()
    public ResponseEntity<ResultViewModel<Boolean>> updateAccount(@RequestBody @Valid AlterAccountUserInputModel input){
        return response(accountService.update(getUser().getId(), input));
    }

    @DeleteMapping()
    public ResponseEntity<ResultViewModel<Boolean>> deleteAccount(){
        return response(accountService.delete(getUser().getId(), getUser().getExpiration()));
    }

    @GetMapping("publications")
    public ResponseEntity<ResultViewModel<List<PublishDetailOutputModel>>> getMy(){
        return response(publishService.get(getUser().getId(), false));
    }
}
