package com.topfox.dao;

import com.topfox.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao2 {
    @Select("select * from user")
    List<User> findAll();
}
