package com.neuedu.controller;

import com.neuedu.Service.IOrderServicer;
import com.neuedu.Service.IUserService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/orderIn")
public class OrderControllerIn {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderServicer iOrderServicer;
    /***
     * 订单发货
     */
    @RequestMapping(value = "/send")
    public ServerResponse sendOrder(Long orderNo, HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        //2.是否管理员
        if(iUserService.isAdminRole(userInfo)){
            //有管理员权限
            return iOrderServicer.sendOrder(orderNo);
        }else{
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());
        }
    }
}
