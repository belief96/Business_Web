package com.neuedu.controller.front;

import com.neuedu.Service.ICartService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping(value = "/add")
    public ServerResponse<CartVO> add(Integer productId, Integer count, HttpSession session) {

        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.addProductToCart(userInfo.getId(), productId, count);
    }

    /**
     * 查询购物车列表
     *
     * @param session
     * @return
     */
    @RequestMapping("/list.do")
    public ServerResponse<CartVO> findCartByUserid(HttpSession session) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.findCartByUserid(userInfo.getId());
    }

    /**
     * 更新购物车某个商品数量
     *
     * @param session
     * @return
     */
    @RequestMapping("/update.do")
    public ServerResponse<CartVO> updateCartByUseridAndProductid(HttpSession session,
                                                                 Integer productid, Integer quantity) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.updateCartByUserAndProductid(userInfo.getId(), productid, quantity);
    }

    /**
     * 删除购物车某个商品
     */
    @RequestMapping("/delete.do")
    public ServerResponse<CartVO> deleteProductFromCart(HttpSession session,
                                                        String productIds) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.deleteProductFromCart(userInfo.getId(), productIds);
    }

    /**
     * 选中某个商品
     */
    @RequestMapping("/check.do")
    public ServerResponse<CartVO> checkedProduct(HttpSession session,
                                                 Integer productid) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.checkedProductByProductId(userInfo.getId(), productid);
    }

    /**
     * 取消选中某个商品
     */
    @RequestMapping("/uncheck.do")
    public ServerResponse<CartVO> uncheckedProduct(HttpSession session,
                                                   Integer productid) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.uncheckedProductByProductId(userInfo.getId(), productid);
    }


    /**
     * 查看商品数量
     */
    @RequestMapping("/findProductNum.do")
    public ServerResponse<Integer> findProductNum(HttpSession session) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.findProductNum(userInfo.getId());

    }

    /**
     * 全选商品
     */
    @RequestMapping("/checkedAll.do")
    public ServerResponse<CartVO> checkedAll(HttpSession session) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.checkAll(userInfo.getId());

    }

    /**
     * 全不选商品
     */
    @RequestMapping("/uncheckedAll.do")
    public ServerResponse<CartVO> uncheckedAll(HttpSession session) {
        //step1:判断用户是否登陆
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo == null) { //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getMsg());
        }
        return iCartService.uncheckAll(userInfo.getId());

    }
}
