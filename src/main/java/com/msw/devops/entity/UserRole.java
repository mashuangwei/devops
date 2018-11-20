package com.msw.devops.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Integer userId;

    private Integer rid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
