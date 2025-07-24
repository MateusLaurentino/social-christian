package com.mla.socialchristian.domain.DTOs.outputmodels;

import java.util.ArrayList;
import java.util.List;

public class ResultViewModel<T> {
    public T result;
    public List<String> errors = new ArrayList<>();
    public boolean sucesso = true;

    public ResultViewModel() { }

    public ResultViewModel(T result) {
        this.result = result;
    }

    public ResultViewModel<T> addResult(T result){
        this.result = result;
        return this;
    }

    public ResultViewModel<T> addErrors(String error){
        this.errors.addLast(error);
        sucesso = false;

        return this;
    }
}
