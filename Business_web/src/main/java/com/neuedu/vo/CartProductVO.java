package com.neuedu.vo;

import com.neuedu.pojo.Category;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 前端购物车商品实体类
 */
public class CartProductVO implements Serializable {

    private Integer productid;  // 商品id
    private Integer user_id;
    private String name; // 商品名称
    private String subtitle; // 商品副标题
    private Integer stock;
    private String main_images; //主图地址，url相对地址
    private String sub_images;  //图片地址，json格式
    private BigDecimal productprice;  //商品价格
    private String limitQuantity;  //库存描述
    private Integer status;   //商品状态
    private Integer cartid;
    private Integer quantity;//商品数量
    private Integer checked;//是否选择
    private BigDecimal totalPrice;//商品总价


    public CartProductVO() {
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_images() {
        return main_images;
    }

    public void setMain_images(String main_images) {
        this.main_images = main_images;
    }

    public String getSub_images() {
        return sub_images;
    }

    public void setSub_images(String sub_images) {
        this.sub_images = sub_images;
    }

    public BigDecimal getProductprice() {
        return productprice;
    }

    public void setProductprice(BigDecimal productprice) {
        this.productprice = productprice;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
