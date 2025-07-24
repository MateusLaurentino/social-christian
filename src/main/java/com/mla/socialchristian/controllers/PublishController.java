package com.mla.socialchristian.controllers;

import com.mla.socialchristian.controllers.base.ControllerBase;
import com.mla.socialchristian.domain.DTOs.inputmodels.AlterPublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.inputmodels.PublishDetailInputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.PublishDetailOutputModel;
import com.mla.socialchristian.domain.DTOs.outputmodels.ResultViewModel;
import com.mla.socialchristian.domain.interfaces.services.IPublishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/publish", produces = "application/json")
public class PublishController extends ControllerBase {

    @Autowired
    private IPublishService publishService;

    @GetMapping()
    public ResponseEntity<ResultViewModel<List<PublishDetailOutputModel>>> getAll(){
        return response(publishService.get(getUser().getId(), true));
    }

    @PostMapping()
    public ResponseEntity<ResultViewModel<PublishDetailOutputModel>> create(@RequestBody @Valid PublishDetailInputModel input){
        return response(publishService.create(getUser().getId(), input));
    }

    @PutMapping()
    public ResponseEntity<ResultViewModel<PublishDetailOutputModel>> alter(@RequestBody @Valid AlterPublishDetailInputModel input){
        return response(publishService.alter(getUser().getId(), input));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResultViewModel<Boolean>> delete(Integer id){
        return response(publishService.delete(getUser().getId(), id));
    }

    @PostMapping("like/{id}")
    public ResponseEntity<ResultViewModel<Boolean>> like(Integer id){
        return response(publishService.like(getUser().getId(), id));
    }
}
