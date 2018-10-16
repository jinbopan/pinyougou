package com.itcast.dao;

import com.itcast.pojo.TUser;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from sys_user")
    public TUser findByUsername(String username);
}
