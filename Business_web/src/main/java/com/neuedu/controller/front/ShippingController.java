package com.neuedu.controller.front;

import com.neuedu.Service.IShippingService;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.vo.ShippingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/shipping")
public class ShippingController {


    @Autowired
    private IShippingService iShippingService;

    @RequestMapping(value = "/addshipping.do")
    public ServerResponse addAddress(HttpSession session, Shipping shipping) {

        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        // 非空判断
        if (shipping.getReceiver_name() == null || shipping.getReceiver_name().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_phone() == null || shipping.getReceiver_phone().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_mobile() == null || shipping.getReceiver_mobile().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_province() == null || shipping.getReceiver_province().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_city() == null || shipping.getReceiver_city().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_district() == null || shipping.getReceiver_district().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_address() == null || shipping.getReceiver_address().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        if (shipping.getReceiver_zip() == null || shipping.getReceiver_zip().equals("")) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        shipping.setUser_id(user.getId());
        return iShippingService.addShipping(shipping);
    }

    @RequestMapping(value = "deleteshipping.do")
    public ServerResponse deleteShipping(HttpSession session, Integer shippingId) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (shippingId == null) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        return iShippingService.deleteShipping(user.getId(), shippingId);
    }

    @RequestMapping(value = "/update")
    public ServerResponse updateShipping(HttpSession session, Integer shippingId, Shipping shipping) {
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (shippingId == null) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        return iShippingService.updateShipping(user.getId(), shippingId, shipping);

    }

    @RequestMapping(value = "/select")
    public ServerResponse<Shipping> selectShipping(HttpSession session, Integer shippingId) {
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        if (shippingId == null) {
            return ServerResponse.createServerResponse(ResponseCode.NOT_NOLL.getCode(), ResponseCode.NOT_NOLL.getMsg());
        }
        return iShippingService.selectShipping(user.getId(), shippingId);
    }

    /**
     * 地址列表
     */
    @RequestMapping(value = "/list")
    public ServerResponse<ShippingVO> shippingList(HttpSession session,
                                                   @RequestParam(defaultValue = "1", required = false) Integer pageNo,
                                                   @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        // 判断用户是否登录
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iShippingService.shippingList(user.getId(), pageNo, pageSize);
    }
}
