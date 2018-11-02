package com.pinyougou;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloBean {
    //将返回值添加到容器中，id名就是方法名
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
