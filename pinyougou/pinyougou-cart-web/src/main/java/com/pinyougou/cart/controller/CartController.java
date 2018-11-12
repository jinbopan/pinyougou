package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinyougou.cart.service.CartService;
import com.pinyougou.order.service.OrderService;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.utils.CookieUtils;
import com.pinyougou.utils.UserAgentUtil;
import com.pinyougou.vo.Cart;
import com.pinyougou.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Reference
    private CartService cartService;

    @Reference
    private OrderService orderService;

    //Cookie 中购物车列表的名称
    private static final String COOKIE_CART_LIST = "PYG_CART_LIST";
    //cookie中购物车列表的最大生存时间
    private static final int COOKIE_CART_LIST_MAX_AGE = 3600 * 24;


    @GetMapping("/getUsername")
    public Map<String, Object> getUsername() {
        // 如果未登录；那么获取到的 username 为： anonymousUser
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("username", username);
        return map;
    }

    @RequestMapping("/findCartList")
    public List<Cart> findCartList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //判断用户是否登录
        List<Cart> cookCartList;
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_CART_LIST, true);
        //fastJSON将字符串集合转成集合
        //判断 cookieValue是否为空
        if (!StringUtils.isEmpty(cookieValue)) {
            cookCartList = JSONArray.parseArray(cookieValue, Cart.class);
        } else {
            cookCartList = new ArrayList<>();
        }
        if ("anonymousUser".equals(username)) {
            //未登录,则操作cookie
            return cookCartList;
        } else {
            //已登录，则操作redis
            List<Cart> redisListCart = cartService.findCartListByUsername(username);
            if (cookCartList != null && cookCartList.size() > 0) {
                redisListCart = cartService.addCookValueToRedis(cookCartList, redisListCart);
                //保存最新的购物车到redis中
                cartService.saveCartListByUsername(redisListCart, username);

                //删除cookie中数据
                CookieUtils.deleteCookie(request, response, COOKIE_CART_LIST);
            }
            return redisListCart;
        }
    }

    @GetMapping("/addItemToCartList")
    //方式二   //设置允许跨域请求  //允许携带并接收cookie
    /*@CrossOrigin(origins = "http://item.pinyougou.com",allowCredentials = "true")*/
    public Result addItemToCartList(Integer num, Long itemId) {
        try {
            //方式一
            //设置允许跨域请求
            response.setHeader("Access-Control-Allow-Origin", "http://item.pinyougou.com");
            //允许携带并接收cookie
            response.setHeader("Access-Control-Allow-Credentials","true" );
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            //获得购物车列表
            List<Cart> cartList = findCartList();
            //将新的商品对应的itemId的加减num更新到原有的购物车列表
            List<Cart> newCartList = cartService.addItemToCartList(cartList, num, itemId);
            //判断用户是否登录
            if ("anonymousUser".equals(username)) {
                String stringCart = JSON.toJSONString(newCartList);
                CookieUtils.setCookie(request, response, COOKIE_CART_LIST, stringCart, COOKIE_CART_LIST_MAX_AGE, true);
            } else {
                //已登录，则操作redis,并且合并cookie中数据
                cartService.saveCartListByUsername(newCartList, username);
            }
            return Result.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("操作失败");
    }

    @PostMapping("/subOrder")
    public Result subOrder(@RequestBody TbOrder order){
        try {
            String userId=SecurityContextHolder.getContext().getAuthentication().getName();
            order.setUserId(userId);
            //当前登录是用pc端还是手机App等登录的
            String info= UserAgentUtil.getRequestHeader(request);
            order.setSourceType(info);
            String outTradeNo=orderService.saveOrder(order);
            return Result.ok(outTradeNo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("提交订单失败");
        }
    }
}
