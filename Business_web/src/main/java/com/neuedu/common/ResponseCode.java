package com.neuedu.common;

//创建一个枚举类
public enum  ResponseCode {

    //成功
    SUCCESS(0,"成功"),
    //需要登录
    NEED_LOGIN(1,"需要登录"),
    //接口返回失败
    FAIL(100,"失败"),
    //获取子类别,category必须传
    GETSUBCATEGORY_NEED_CATEGORYID(3,"categoryId必须"),
    GETSUBCATEGORY_NEED_CATEGORYNAME(4,"类别名称必须"),
    //需要商品
    NEED_PRODUCT(5,"商品参数必须"),
    //status商品参数必须
    NEED_PRODUCT_STATUS(6,"status商品参数必须"),
    //没有权限操作
    NO_PERMISSION(2,"无权限"),
    //商品数量不能低于1
    NO_CUT(9,"该宝贝不能减少了哟！~"),
    NEED_ORDERNO(10,"需要订单号！"),
    NOT_FOUND_ORDERNO(11,"没有该订单！"),
    CART_EMPTY(12,"购物车为空！"),
    PRODUCT_STOCK(13,"库存不足！"),
    NOT_NOLL(14,"库存不足！"),
    ORDER_NOT_CANCEL(15,"该订单无法取消！"),
    CART_NOT_FOUND(16,"购物车无商品！"),
    PRODUCT_OFFLINE(8,"商品不存在或已经下架");



    private int code;
    private String msg;

    private ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
