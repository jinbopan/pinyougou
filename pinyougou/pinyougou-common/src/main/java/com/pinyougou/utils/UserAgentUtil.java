package com.pinyougou.utils;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtil {
    public static String getRequestHeader(HttpServletRequest request){
        // 从浏览器获取请求头信息
        String info= request.getHeader("user-agent");
        if(info.contains("Windows")){
            return "2";
        }

        if(info.contains("Macintosh")){
            System.out.println("Mac pc端登陆");
            return "1";
        }

        if(info.contains("Android")) {
            System.out.println("Android移动客户端");
            return "3";
        }

        if(info.contains("iPhone")) {
            System.out.println("iPhone移动客户端");
            return "4";
        }

        if(info.contains("iPad")) {
            System.out.println("iPad客户端");
            return "5";
        }
        return "6";
    }
}
