package com.itcast.service;

import com.itcast.pojo.TUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UerService extends UserDetailsService{
    public TUser findByUsername(String username);
}
