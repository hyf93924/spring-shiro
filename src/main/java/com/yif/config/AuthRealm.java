/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.config;

import com.yif.dao.UserMapper;
import com.yif.entity.Permission;
import com.yif.entity.Role;
import com.yif.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author heyif
 * @since v1.0 2018-10-29T20:47
 */
public class AuthRealm extends AuthorizingRealm {

    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * 获取授权信息
     * 权限认证
     *
     * @param principalCollection  已经登录用户信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从session中获取当前的登录用户
        User user = (User) getAvailablePrincipal(principalCollection);
        /**
         * 获取角色、权限列表
         */
        List<String> roleNameList = new ArrayList<>();
        List<String> permitNameList = new ArrayList<>();
        // 获取用户的角色列表
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roleNameList.add(role.getRname());
                // 根据角色获取权限，并加入权限列表：permitList
                Set<Permission> permitSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permitSet)) {
                    for (Permission permit : permitSet) {
                        permitNameList.add(permit.getName());
                    }
                }
            }
        }

        //新建shiro授权类，并设置权限列表
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permitNameList);
        info.addRoles(roleNameList);

        return info;
    }

    /**
     * 获取身份验证信息
     * 身份认证
     *
     * @param authenticationToken   用户身份信息token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库获取响应的用户名密码
        User user = userMapper.findByUsername(token.getUsername());
        if (user==null){
            return null;
        }
        // 返回结果，交给 shiro 处理，需要用户对象、用户密码、本类的类名
        return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());

    }
}
