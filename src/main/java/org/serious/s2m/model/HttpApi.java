/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.serious.s2m.model;

import java.util.List;

import io.swagger.models.parameters.Parameter;

/**
 * @author : maduo
 * @date : 2018/9/12 上午10:47
 */
public class HttpApi {
    private String path;
    private String requestMethod;
    private String summary;
    private String description;
    private List<Parameter> parameterList;
    private ResponseModel responseModel;

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

}
