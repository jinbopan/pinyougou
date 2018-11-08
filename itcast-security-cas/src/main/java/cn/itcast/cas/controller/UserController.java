package cn.itcast.cas.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/getUsername")
    public String getUsername(){
        String username =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
