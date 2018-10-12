package com.neuedu.Service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.vo.ShippingVO;

public interface IShippingService {

    /**
     * 添加地址
     */
    public ServerResponse addShipping(Shipping shipping);

    /**
     * 删除地址
     */
    public ServerResponse deleteShipping(Integer userId,Integer shippingId);


    /**
     * 修改收货地址
     */
    public ServerResponse updateShipping(Integer userId,Integer shippingId,Shipping shipping);

    /**
     * 根据收货地址id查询收货地址详细信息
     */
    public ServerResponse<Shipping> selectShipping(Integer userId , Integer shippingId);

    /**
     * 查询地址列表
     */
    public ServerResponse<ShippingVO> shippingList(Integer userId , Integer pageNo , Integer pageSize);

}
