package com.pinyougou.service;

import com.pinyougou.pojo.TUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UerService extends UserDetailsService{
    public TUser findByUsername(String username);
}
