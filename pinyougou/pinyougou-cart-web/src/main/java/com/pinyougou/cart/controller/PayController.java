package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.order.service.OrderService;
import com.pinyougou.pay.service.WeixinPayService;
import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Reference
    private OrderService orderService;
    @Reference
    private WeixinPayService weixinPayService;



    @GetMapping("/createNative")
    public Map<String, String> createNative(String outTradeNo) {
        TbPayLog payLog = orderService.findPayLogByOutTradeNo(outTradeNo);
        if (payLog != null) {
            // 到支付系统进行提交订单并返回支付地址
            return weixinPayService.createNative(outTradeNo, payLog.getTotalFee().toString());
        }
        return new HashMap();
    }

    @GetMapping("/searchPayStatus")
    public Result searchPayStatus(String outTradeNo) {
        //根据支付日志id（交易号）在3分钟里面每隔3秒钟到支付系统查询该订单的支付状态
        Result result=Result.fail("支付失败");
        try {
            int count = 0;
            while (true) {
                Map<String, String> map = weixinPayService.searchPayStatus(outTradeNo);
                if(map==null){
                    break;
                }
                if(map.get("trade_state").equals("SUCCESS")){
                    //支付成功，则修改支付日志和所有订单状态为已支付，并修支付时间
                    orderService.updatePyStatus(outTradeNo,map.get("transaction_id"));
                    result=Result.ok("支付成功");
                    break;
                }
                //每三秒查询一次
                Thread.sleep(3000);
                count++;
                if(count>9){
                    result=Result.fail("生成二维码超时");
                    break;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
