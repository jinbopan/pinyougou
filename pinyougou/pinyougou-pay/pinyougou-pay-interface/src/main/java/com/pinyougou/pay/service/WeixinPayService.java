package com.pinyougou.pay.service;

import java.util.Map;

public interface WeixinPayService {
    Map createNative(String outTradeNo, String toString);

    Map<String,String> searchPayStatus(String outTradeNo);

}
