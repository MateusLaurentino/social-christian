package com.mla.socialchristian.domain.DTOs.outputmodels;

import com.mla.socialchristian.domain.entities.AccountUserEntity;
import java.util.Date;

public class AccountUserOutputModel {
    public String name;
    public Date birthday;

    public AccountUserOutputModel(AccountUserEntity accountUser){
        this.name = accountUser.getName();
        this.birthday = accountUser.getBirthday();
    }
}
