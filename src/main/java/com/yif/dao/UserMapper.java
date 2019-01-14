/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.dao;

import com.yif.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author heyif
 * @since v1.0 2018-10-29T20:56
 */
@Repository
@Mapper
public interface UserMapper {

//    String getPassword(String username);
//
//    String getRole(String username);
//
//    List<User> getUserList();

    User findByUsername(@Param("username") String username);

}
