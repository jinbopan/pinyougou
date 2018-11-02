package com.pinyougou;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootController {
    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }
}
