package com.neuedu.Service;

import com.neuedu.common.ServerResponse;
import com.neuedu.vo.CartVO;

public interface ICartService {
    /**
     * 添加商品的购物车
     */
    public ServerResponse<CartVO> addProductToCart(Integer userid,Integer product,int count);

    /**
     *查询用户购物信息
     */
    public ServerResponse<CartVO> findCartByUserid(Integer userid);

    /**
     * 更新购物车某个商品数量
     */
    public ServerResponse<CartVO> updateCartByUserAndProductid(Integer userid,Integer productid,Integer quantity);

    /**
     * 删除购物车某个商品
     */
    public ServerResponse<CartVO> deleteProductFromCart(Integer userid,String productIds);

    /**
     * 选中某个商品
     */
    public ServerResponse<CartVO> checkedProductByProductId(Integer userid,Integer productid);

    /**
     * 取消选中某个商品
     */
    public ServerResponse<CartVO> uncheckedProductByProductId(Integer userid,Integer productid);

    /**
     * 查询购物车商品数量
     */
    public ServerResponse<Integer> findProductNum(Integer userid);

    /**
     * 购物车全选
     */
    public ServerResponse<CartVO> checkAll(Integer userid);

    /**
     * 取消购物车全选
     */
    public ServerResponse<CartVO> uncheckAll(Integer userid);
}
