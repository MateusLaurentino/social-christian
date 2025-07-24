package com.mla.socialchristian.domain.interfaces.services;

import com.mla.socialchristian.domain.DTOs.inputmodels.AlterPublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.PublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.PublishDetailOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;

import java.util.List;

public interface IPublishService {
    ResultViewModel<List<PublishDetailOutputModel>> get(Integer idUser, boolean getAll);
    ResultViewModel<PublishDetailOutputModel> create(Integer idUser, PublishDetailInputModel input);
    ResultViewModel<PublishDetailOutputModel> alter(Integer idUser, AlterPublishDetailInputModel input);
    ResultViewModel<Boolean> delete(Integer idUser, Integer id);
    ResultViewModel<Boolean> like(Integer idUser, Integer id);
}
