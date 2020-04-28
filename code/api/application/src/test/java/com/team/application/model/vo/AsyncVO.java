package com.team.application.model.vo;

import org.springframework.web.context.request.async.DeferredResult;


public class AsyncVO<I, O> {
    private I params;
    private DeferredResult<O> result;

    public I getParams() {
        return params;
    }

    public void setParams(I params) {
        this.params = params;
    }

    public DeferredResult<O> getResult() {
        return result;
    }

    public void setResult(DeferredResult<O> result) {
        this.result = result;
    }
}
