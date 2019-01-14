/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 *
 * @author heyif
 * @since v1.0 2018-10-30T9:34
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3128010951758975005L;

    // 唯一编号
    private Integer uid;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 角色列表
    private Set<Role> roles = new HashSet<>();

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
