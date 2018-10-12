package com.neuedu.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 前端购物车实体类
 */
public class CartVO implements Serializable{
    private List<CartProductVO> cartProductVOList;
    private  boolean isallchecked;
    private BigDecimal totalPrice;

    public CartVO() {

    }


    public CartVO(List<CartProductVO> cartProductVOList, boolean isallchecked, BigDecimal totalPrice) {
        this.cartProductVOList = cartProductVOList;
        this.isallchecked = isallchecked;
        this.totalPrice = totalPrice;
    }


    public List<CartProductVO> getCartProductVOList() {
        return cartProductVOList;
    }

    public void setCartProductVOList(List<CartProductVO> cartProductVOList) {
        this.cartProductVOList = cartProductVOList;
    }

    public boolean isIsallchecked() {
        return isallchecked;
    }

    public void setIsallchecked(boolean isallchecked) {
        this.isallchecked = isallchecked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
