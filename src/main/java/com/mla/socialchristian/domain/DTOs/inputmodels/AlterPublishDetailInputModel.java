package com.mla.socialchristian.domain.DTOs.inputmodels;

import jakarta.validation.constraints.*;

public class AlterPublishDetailInputModel extends PublishDetailInputModel {
    @NotNull(message = "CampoObrigatorio")
    @Positive(message = "MaiorZero")
    public Integer Id;
}
