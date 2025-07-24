package com.mla.socialchristian.controllers.base;

import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.security.LoggedUserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class ControllerBase {
    protected LoggedUserModel getUser() {
        var context = SecurityContextHolder.getContext();
        if (context == null) return null;

        var auth = context.getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;

        var principal = auth.getPrincipal();

        if (principal instanceof LoggedUserModel) {
            return (LoggedUserModel)principal;
        }

        return null;
    }

    protected <T> ResponseEntity<ResultViewModel<T>> response(ResultViewModel<T> result)  {
        if (result.sucesso) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
