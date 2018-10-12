package com.neuedu.dao;

import com.neuedu.pojo.Shipping;

import java.util.List;


public interface IShippingDao {

    /**
     * 添加地址
     */
    public Integer addShipping(Shipping shipping);

/**
 *删除地址
 */
    public Integer deleteShipping(Integer shippingId,Integer userId);


    /**
     * 登录状态下修改地址
     */
    public Integer updateShipping(Integer userId, Integer shippingId, Shipping shipping);

    /**
     * 查找收货地址详情
     */
    public Shipping selectShipping(Integer userId , Integer shippingId);

    /**
     * 查询收货地址列表
     */
    public List<Shipping> shippingList(Integer userId , Integer pageNo , Integer pageSize);

}
