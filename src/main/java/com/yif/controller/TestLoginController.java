/**
 * 6006.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.yif.controller;

import com.yif.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author heyif
 * @since v1.0 2019-01-08T15:01
 */
@RestController
public class TestLoginController {

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 主页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        model.addAttribute("user", user);
        return "index";
    }

    /**
     * 用户登陆逻辑
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "redirect:/index";
        } catch (Exception e) {
            return "login";
        }
    }

    /**
     * 用户注销逻辑
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    /**
     * 无权限访问页面
     * @return
     */
    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    /**
     * 管理员专属页面，只有管理员角色可以访问
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * 拥有“修改”权限，才能访问该接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public String edit() {
        return "edit success";
    }

    /**
     * 拥有“查询”权限，才能访问该接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/query")
    public String query() {
        return "query success";
    }
}
