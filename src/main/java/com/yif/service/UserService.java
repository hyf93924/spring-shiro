/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.service;


import com.yif.entity.User;

/**
 * @author heyif
 * @since v1.0 2018-10-30T9:57
 */
public interface UserService {

//    List<User> findByUsername(String username);
    User findByUsername(String username);
}
