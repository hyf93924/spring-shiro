/**
 * create by HeYif.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.yif;

import com.yif.dao.UserMapper;
import com.yif.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyif
 * @since v1.0 2019-01-14T9:26
 */
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void userMapperTest(){
        String username = "demo";
        User user = userMapper.findByUsername(username);
        System.out.println(user);
    }
}
