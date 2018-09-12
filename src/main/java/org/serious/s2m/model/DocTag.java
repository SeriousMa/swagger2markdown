/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.serious.s2m.model;

import java.util.List;

/**
 * @author : maduo
 * @date : 2018/9/12 上午10:49
 */
public class DocTag {
    String name;
    String description;
    List<HttpApi> httpApiList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HttpApi> getHttpApiList() {
        return httpApiList;
    }

    public void setHttpApiList(List<HttpApi> httpApiList) {
        this.httpApiList = httpApiList;
    }
}
