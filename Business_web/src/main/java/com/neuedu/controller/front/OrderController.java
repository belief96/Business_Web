package com.neuedu.controller.front;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.neuedu.Service.IOrderServicer;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    IOrderServicer iOrderServicer;

    @RequestMapping(value = "pay.do")
    public ServerResponse pay(Long orderNo, HttpSession session) {

        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.pay(orderNo, userInfo.getId());
    }


    @RequestMapping(value = "/callback.do")

    public String alipay_calback(HttpServletRequest request) {

        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> params = new HashMap<>();

        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            //map-->key
            String key = iterator.next();
            String[] values = (String[]) map.get(key);
            String value = "";
            for (int i = 0; i < values.length; i++) {
                value = (i == values.length - 1 ? value + values[i] : value + values[i] + ",");
            }
            params.put(key, value);
        }
        //支付宝验签
        try {
            params.remove("sign_type");
            boolean result= AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            if(result){//支付宝回调该接口
            return iOrderServicer.alipaycallback(params);
            }else{
                return "fail";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping(value = "query_order_pay_status.do")
    public ServerResponse query_order_pay_status(Long orderNo){

    return iOrderServicer.query_order_pay_status(orderNo);
    }

    @RequestMapping(value = "create.do")
    public ServerResponse createOrder(Integer shippingid,HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.createOrder(shippingid,userInfo.getId());
    }

    /***
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder")
    public ServerResponse cancelOrder(Long orderNo,HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.cancelOrder(orderNo,userInfo.getId());
    }

    /**
     * 获取订单商品信息
     */
    @RequestMapping(value = "/getcartProductInfo")
    public ServerResponse getcartProductInfo(HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.getCartProductInfo(userInfo.getId());
    }

    /**
     * 查看订单详细
     */
    @RequestMapping(value = "/getorderdetail")
    public ServerResponse getOrderDetail(HttpSession session,Long orderNo){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.getorderdetail(orderNo,userInfo.getId());
    }

    /**
     * 订单List
     */
    @RequestMapping(value = "/list")
    public ServerResponse orderList(HttpSession session,
                                                @RequestParam(defaultValue = "1", required = false) Integer pageNo,
                                                @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.orderList(user.getId(), pageNo, pageSize);
    }

    /**
     * 根据订单号查看订单详细
     */
    @RequestMapping(value = "/findOrderitem")
    public ServerResponse findOrderitem(HttpSession session,Long orderNo){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iOrderServicer.findOrderItem(orderNo,userInfo.getId());
    }
}
