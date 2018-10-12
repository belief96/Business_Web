package com.neuedu.controller;

import com.neuedu.Service.IProductService;
import com.neuedu.Service.IUserService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService iProductService;

    /**
     * 添加或更新商品
     */
    @RequestMapping("/add")
    public ServerResponse<String> addProduct(Product product, HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.addorUpdateProduct(product);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }


    /**
     * 商品的上下架
     */
    @RequestMapping("/onlineoroffline")
    public ServerResponse<String> onlineoroffline(Integer productId,Integer status, HttpSession session){
        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.onlineoroffline(productId,status);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }


    /**
     * 商品详情
     */
    @RequestMapping(value = "/detail")
    public ServerResponse<ProductVO> productDetail(Integer productId, HttpSession session){

        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.findProductById(productId);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }

    }

    /**
     * 分页查询商品
     */
    @RequestMapping(value = "/findproductbypageno")
    public ServerResponse<PageModel<ProductVO>> findProductByPageNo(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, HttpSession session){

        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.findProductByPageNo(pageNo,pageSize);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }

    }

    /**
     * 分页搜索商品
     */
    @RequestMapping(value = "/search")
    public ServerResponse<PageModel<ProductVO>> search(@RequestParam(required = false) Integer productid,@RequestParam(required = false)String productname,
                                                       @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize" ,defaultValue = "10")
                                                       Integer pageSize, HttpSession session){

        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.findPrdouctByPrdouctIdOrProductName(productid,productname,pageNo,pageSize);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }

    }

    // 图片上传接口
    @RequestMapping(value = "/upload")
    public ServerResponse<String> upload(MultipartFile upload, HttpSession session){

        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return iProductService.upload(upload);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }

    }

}
