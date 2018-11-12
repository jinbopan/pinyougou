package com.pinyougou.order.service;

import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;

public interface OrderService extends BaseService<TbOrder> {

    PageResult search(Integer page, Integer rows, TbOrder order);

    String saveOrder(TbOrder order);

    /**
     * 根据outTradeNo查询支付日志
     * @param outTradeNo
     * @return
     */
    TbPayLog findPayLogByOutTradeNo(String outTradeNo);

    void updatePyStatus(String outTradeNo, String transaction_id);
}