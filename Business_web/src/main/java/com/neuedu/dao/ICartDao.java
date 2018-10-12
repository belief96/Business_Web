package com.neuedu.dao;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Cart;
import com.neuedu.vo.CartVO;

import java.util.List;

public interface ICartDao {
    /**
     * 根据userid和productid查询购物车中的购物信息
     */
    public Cart findCartByUseridAndProductid(Integer userid, Integer productid);

    /**
     * 添加购物信息
     */
    public Integer addProductToCart(Cart cart);

    /**
     * 更新购物车中商品数量
     */
    public Integer updateCartByUseridAndProductid(Cart cart);

    /**
     * 根据userid查询购物集合
     */
    public List<Cart> findCartByUserid(Integer userid);


    /**
     *判断购物车全选
     */
    public Integer isCheckAll(Integer userid);

    /**
     * 移除购物车某个商品
     */
    public Integer deleteProduct(List<Integer> productIds,Integer userid);

    /**
     * 选中购物车某个商品
     */
    public Integer checkProductByProductId(Integer userid,Integer productid);

    /**
     * 取消选中购物车某个商品
     */
    public Integer uncheckProductByProductId(Integer userid,Integer productid);

    /**
     * 查询用户已选中的购物车信息
     */
    public List<Cart> findCheckedCartsByUserId(Integer userid);

    /**
     * 移除已下单的购物车商品
     */
    public Integer removeCheckedProduct(List<Cart> cartList,Integer userid);
}
