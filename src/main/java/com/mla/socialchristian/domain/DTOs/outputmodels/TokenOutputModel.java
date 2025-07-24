package com.mla.socialchristian.domain.DTOs.outputmodels;

import java.util.Date;

public class TokenOutputModel {
    public String Token;
    public Date Expiration;

    public TokenOutputModel(Date expiration) {
        Expiration = expiration;
    }
}
