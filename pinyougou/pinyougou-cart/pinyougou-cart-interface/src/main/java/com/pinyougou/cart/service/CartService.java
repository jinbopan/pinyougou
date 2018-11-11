package com.pinyougou.cart.service;

import com.pinyougou.vo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> addItemToCartList(List<Cart> cartList, Integer num, Long itemId);

    List<Cart> findCartListByUsername(String username);

    void saveCartListByUsername(List<Cart> newCartList, String username);

    List<Cart> addCookValueToRedis(List<Cart> cookCartList, List<Cart> redisListCart);
}
