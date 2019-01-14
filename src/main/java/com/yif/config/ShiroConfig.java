/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Shiro配置类
 *
 * @author heyif
 * @since v1.0 2018-10-29T20:30
 */
@Configuration
public class ShiroConfig {

    /***************** shiro基础配置 *****************/
    /**
     * 配置shiro拦截器
     * @param manager 已在 Spring 容器中注册的“安全管理器”
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        // 定义shiro拦截器工厂bean
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        bean.setSecurityManager(manager);

        // 设置登陆url
        bean.setLoginUrl("/login");
        // 设置登陆跳转后的url
        bean.setSuccessUrl("/index");
        // 设置无权限访问的url
        bean.setUnauthorizedUrl("/unauthorized");

        // 定义拦截规则
        LinkedHashMap<String, String> definitionMap = new LinkedHashMap<>();
        definitionMap.put("/index", "authc");
        definitionMap.put("/login", "anon");
        definitionMap.put("/loginUser", "anon");
        definitionMap.put("/admin", "roles[admin]");
        definitionMap.put("/edit", "perms[edit]");
        definitionMap.put("/query", "perms[query]");
        definitionMap.put("/druid/**", "anon");
        definitionMap.put("/**", "user");
        // 设置拦截规则
        bean.setFilterChainDefinitionMap(definitionMap);

        return bean;
    }

    /**
     * 配置安全管理器
     * @param authRealm 已在 Spring 容器中注册的“用户域”
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        // 新建web管理器
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        // 设置“用户域”
        manager.setRealm(authRealm);

        return manager;
    }

    /**
     * 配置用户域
     * @param matcher 已在 Spring 容器中注册的“密码校验类”
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        // 定义用户域
        AuthRealm authRealm = new AuthRealm();

        // 设置密码校验规则
        authRealm.setCredentialsMatcher(matcher);

        // 设置缓存管理器
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());

        return authRealm;
    }

    /**
     * 配置shiro密码校验类
     * @return
     */
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    /***************** shiro && spring 关联 *****************/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);

        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);

        return creator;
    }
}
