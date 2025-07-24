package com.mla.socialchristian.controllers;

import com.mla.socialchristian.controllers.base.ControllerBase;
import com.mla.socialchristian.domain.DTOs.inputmodels.AccountUserInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.TokenInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.TokenOutputModel;
import com.mla.socialchristian.domain.interfaces.services.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account", produces = "application/json")
public class AccountController extends ControllerBase {

    @Autowired
    private IAccountService accountService;

    @PostMapping()
    public ResponseEntity<ResultViewModel<Boolean>> create(@RequestBody @Valid AccountUserInputModel input){
        return response(accountService.createUser(input));
    }

    @PostMapping("access")
    public ResponseEntity<ResultViewModel<TokenOutputModel>> token(@RequestBody @Valid TokenInputModel input){
        return response(accountService.generateToken(input));
    }
}
