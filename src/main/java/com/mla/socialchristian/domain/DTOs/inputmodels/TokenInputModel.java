package com.mla.socialchristian.domain.DTOs.inputmodels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class TokenInputModel {
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email informado é inválido")
    public String email;

    @NotBlank(message = "A senha é obrigatória.")
    public String password;
}
