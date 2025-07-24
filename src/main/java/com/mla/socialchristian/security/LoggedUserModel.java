package com.mla.socialchristian.security;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

public class LoggedUserModel {
    private Integer id;
    private String name;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public List<String> getRoles() {
        return roles;
    }

    public LoggedUserModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public LoggedUserModel setName(String name) {
        this.name = name;
        return this;
    }

    public LoggedUserModel setDates(Long currentTime, Long expiration) {
        this.issuedAt = new Date(currentTime);
        this.expiration = new Date(currentTime + expiration);
        return this;
    }

    public LoggedUserModel setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
