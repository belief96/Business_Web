package com.neuedu.dao;

import com.neuedu.pojo.*;

import java.util.List;

public interface IOrderDao {
    /**
     * 查询订单信息
     * @param orderNo
     * @param userid
     * @return
     */
    public Order findOrderByOrderNoAndUserid(Long orderNo, Integer userid);

    /**
     * 查询订单信息
     * @param orderNo
     * @return
     */
    public Order findOrderByOrderNo(Long orderNo);


    /***
     * 根据订单编号查询订单明细
     * @param orderNo
     * @return
     */
    public List<OrderItem> findOrderItemsByOrderNo(Long orderNo);

    /**
     * 修改订单支付状态
     */
    public Integer updateOrderStatusByOrderNo(Integer status,Long orderno );

    /**
     * 添加支付信息
     */
    public Integer addPayInfo(PayInfo payInfo);

    /**
     * 添加订单接口
     */
    public  Integer addOrder(Order order);

    /**
     * 批量插入订单明细
     */
    public Integer barchInsertOrderItem(List<OrderItem> orderItemList);

    /**
     * 修改订单支付状态
     */
    public Integer updateOrderStatusByOrderNoAndUserid(Integer status,Long orderno,Integer userid);

    /**
     * 发货接口
     */
    public Integer sendOrder(Order order);

    /**
     * 订单List
     */
    public List<Order> orderList(Integer userId , Integer pageNo , Integer pageSize);

    /**
     * 计算总页数
     */
    public Long totalPage(Integer userId);

    /**
     * 根据订单号查看详细
     */
    public List<OrderItem> findOrderItemByOrderNo(Long orderNo,Integer userId);

}
