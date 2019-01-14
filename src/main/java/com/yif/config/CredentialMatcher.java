/**
 * 6006.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.yif.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author heyif
 * @since v1.0 2019-01-08T15:33
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authToken, AuthenticationInfo info) {
        // 获取用户登陆填写信息
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;

        // 获取登陆密码 && 正确密码
        String password = new String(token.getPassword());
        String dbPassword = (String) info.getCredentials();

        // 判断密码是否相等
        return this.equals(password, dbPassword);
    }
}
