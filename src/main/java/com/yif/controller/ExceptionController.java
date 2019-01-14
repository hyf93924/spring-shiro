/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;

/**
 * 异常捕获
 *
 * @author heyif
 * @since v1.0 2018-11-05T20:02
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccountException.class)
    public String handleShiroException(Exception ex){
        return "异常";
    }
}
