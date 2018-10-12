package com.neuedu.controller;

import com.neuedu.Service.ICategoryService;
import com.neuedu.Service.IUserService;
import com.neuedu.Service.impl.CategoryServiceImpl;
import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
   private IUserService userService;

    @Autowired
    ICategoryService categoryService;


    //添加类别
    @RequestMapping("/add")
    public ServerResponse<String> addCategory(@RequestParam(name = "parentId",required = true,defaultValue = "0") Integer parentId,
                            @RequestParam(name = "categoryName") String categoryName, HttpSession session){

        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
          if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
              return categoryService.addCategory(parentId,categoryName);
          }else {
              return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

          }
    }

    @RequestMapping(value = "/findsubcategory")
    public ServerResponse<List<Category>> findSubCategory(@RequestParam(required = false,defaultValue = "0")Integer categoryId,HttpSession session){


        if(categoryId==null){
            return ServerResponse.createServerResponse(ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getCode(),ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getMsg());

        }
        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return categoryService.findSubCategoryById(categoryId);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }


    @RequestMapping(value = "/updatecategory")
    public ServerResponse<String> updateCategory(Integer categoryId,String categoryName,HttpSession session){


        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            return categoryService.updateCategory(categoryId,categoryName);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }

    //查询后代类别
    @RequestMapping(value = "/findallchild")
    public ServerResponse<Set<Integer>> findallchild(Integer categoryId,HttpSession session){


        //step1:判断用户是否登陆
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){ //没有登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        //step2:判断用户是否有权限
        if(userService.isAdminRole(userInfo)){
            // 有管理员权限，可以添加类别
            Set<Integer> set=new HashSet<>();
            set=categoryService.findAllChildByCategory(set,categoryId);
            if(categoryId==null){
                return ServerResponse.createServerResponse(ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getCode(),ResponseCode.GETSUBCATEGORY_NEED_CATEGORYID.getMsg());
            }
            return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),set);
        }else {
            return ServerResponse.createServerResponse(ResponseCode.NO_PERMISSION.getCode(),ResponseCode.NO_PERMISSION.getMsg());

        }
    }

}
