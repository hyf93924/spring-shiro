/**
 * 6006.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.yif.entity;

import java.io.Serializable;

/**
 * 权限
 *
 * @author heyif
 * @since v1.0 2019-01-08T14:42
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -8200858886885280058L;

    // 唯一编号
    private Integer pid;

    // 权限标识
    private String name;

    // 权限对应的url
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
