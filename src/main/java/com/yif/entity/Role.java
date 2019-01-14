/**
 * 6006.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.yif.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 *
 * @author heyif
 * @since v1.0 2019-01-08T14:40
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 6953332826004791820L;

    // 唯一编号
    private Integer rid;

    // 角色名称
    private String rname;

    // 角色包含的权限集合
    private Set<Permission> permissions = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
