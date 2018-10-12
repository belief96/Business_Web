package com.neuedu.Service;

import com.neuedu.common.ServerResponse;

import java.util.Map;

public interface IOrderServicer {

    public ServerResponse pay(Long orderNo,Integer userid);

    public String alipaycallback(Map<String,String> map);

    public ServerResponse query_order_pay_status(Long orderNo);

    /**
     * 创建订单
     */
    public ServerResponse createOrder(Integer shippingid,Integer userid);

    /**
     * 取消订单
     */
    public ServerResponse cancelOrder(Long orderNo,Integer userid);

    /**
     * 获取订单商品信息
     */
    public ServerResponse getCartProductInfo(Integer userid);

    /**
     * 获取订单详细
     */
    public ServerResponse getorderdetail(Long orderNo,Integer userid);

    /**
     * 订单发货
     */
    public ServerResponse sendOrder(Long orderNo);

    /**
     * 订单List
     */
    public ServerResponse orderList(Integer userId , Integer pageNo , Integer pageSize);

    /**
     * 总页数
     */
    public Long totalPage(Integer userId,Integer pageNo,Integer pageSize);

    /**
     * 根据订单号获取订单详细
     */
    public ServerResponse findOrderItem(Long orderNo,Integer userid);
}
