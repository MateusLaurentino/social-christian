package com.mla.socialchristian.domain.DTOs.inputmodels;

import jakarta.validation.constraints.*;
import java.util.Date;

public class AccountUserInputModel {
    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 100, message = "TamanhoMaximo")
    public String name;

    @NotBlank(message = "CampoObrigatorio")
    @Email(message = "CampoInvalido")
    public String email;

    @NotNull(message = "CampoObrigatorio")
    @Past(message = "CampoInvalido")
    public Date birthday;

    @NotBlank(message = "CampoObrigatorio")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$",
            message = "SenhaInvalida"
    )
    public String password;
}