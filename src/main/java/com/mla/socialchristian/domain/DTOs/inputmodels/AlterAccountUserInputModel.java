package com.mla.socialchristian.domain.DTOs.inputmodels;

import jakarta.validation.constraints.*;

import java.util.Date;

public class AlterAccountUserInputModel {
    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 100, message = "TamanhoMaximo")
    public String name;

    @NotNull(message = "CampoObrigatorio")
    @Past(message = "CampoInvalido")
    public Date birthday;
}
