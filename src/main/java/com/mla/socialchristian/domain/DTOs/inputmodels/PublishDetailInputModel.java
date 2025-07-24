package com.mla.socialchristian.domain.DTOs.inputmodels;

import jakarta.validation.constraints.*;

public class PublishDetailInputModel {
    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 100, message = "TamanhoMaximo")
    public String bookReference;

    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 1000, message = "TamanhoMaximo")
    public String chapterMessage;

    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 150, message = "TamanhoMaximo")
    public String title;

    @NotBlank(message = "CampoObrigatorio")
    @Size( min = 1, max = 5000, message = "TamanhoMaximo")
    public String conclusion;
}
