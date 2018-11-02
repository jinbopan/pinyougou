package com.pinyougou.dao;

import com.pinyougou.pojo.TUser;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from sys_user")
    public TUser findByUsername(String username);
}
