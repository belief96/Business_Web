package com.neuedu.controller;

import com.neuedu.Service.IUserService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.IpUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService userService;

/*    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String operation=request.getParameter("operation");
        if(operation==null||operation.equals("")){
            throw BusinessException.createException(request.getSession(),"operation不可为空","3s后自动返回","operation.jsp");
        }
        if(operation.equals("1")){
            //执行登录操作
            login(request, response);

        }else if(operation.equals("2")){
            //执行注册操作
            register(request,response);
        }else if(operation.equals("3")){
            //忘记密码查询密保问题
            findQuestionByUsername(request, response);

        }else if(operation.equals("4")){
            //效验答案
            checkAnswer(request, response);
        }else if(operation.equals("5")){
            //忘记创建密码
            updatePassword(request, response);
        }
    }*/

    @RequestMapping(value = "/logout")
    //用户退出登录
    public ServerResponse<UserInfo> logout(HttpSession session){
        //判断是否登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        if(userInfo!=null){
            session.removeAttribute(Const.CURRENTUSER);
        }
        //token清空
        if(userInfo!=null){
            userService.updateTokenById(userInfo.getId(),"");
        }

       return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
    }

    @RequestMapping(value = "/userinfo")
    //查看用户信息
    public ServerResponse<UserInfo> selectUser(HttpSession session){
        //判断用户是否登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            //登录过期或未登录
            return ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        System.out.println("用户信息"+userInfo);
        return ServerResponse.createServerResponse(0,"成功",userInfo);
    }
    //登录状态下重置密码
    @RequestMapping(value = "/setpassword")
    public ServerResponse<UserInfo> updatePasswordlogin(@RequestParam(value = "oldpassword") String oldpassword,
                                    @RequestParam(value = "newpassword") String newpassword,HttpSession session){

        if(oldpassword==null||oldpassword.equals("")){
            return ServerResponse.createServerResponse(1,"旧密码不能为空");
        }
        if(newpassword==null||newpassword.equals("")){
            return ServerResponse.createServerResponse(2,"新密码不能为空");
        }

        //判断用户是否登录
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){//未登录或者登陆已过期
            return ServerResponse.createServerResponse(4,"你还没有登录，请先登录");
        }
        return userService.updatePassword(oldpassword,newpassword,userInfo);
    }


    //忘记密码的创建新密码
    @RequestMapping(value = "/updatep")
    public ServerResponse<String> updatePassword(String username,String newpassword,String token){

        if(username==null||username.equals("")){
            return ServerResponse.createServerResponse(1,"用户名不能为空");
        }
        if(newpassword==null||newpassword.equals("")){
            return ServerResponse.createServerResponse(2,"密码不能为空");
        }
        if(token==null||token.equals("")){
            return ServerResponse.createServerResponse(3,"token不能为空");
        }

        return userService.updatePassword(username,MD5Utils.GetMD5Code(newpassword),token);

    }


    //效验答案
    @RequestMapping(value = "/checka")
    public ServerResponse<String> checkAnswer(String username,String question,String answer){

        if(username==null||username.equals("")){
            return ServerResponse.createServerResponse(1,"用户名不能为空！");
        }
        if(question==null||question.equals("")){
            return ServerResponse.createServerResponse(1,"密保问题不能为空！");

        }
        if(answer==null||answer.equals("")){
            return ServerResponse.createServerResponse(1,"答案不能为空！");
        }

        //调用service层
        return userService.chackAnswer(username,question,answer);
    }


    //查询密保问题
    @RequestMapping(value = "/findq")
    public ServerResponse<String> findQuestionByUsername(HttpSession session,String username){
        if(username==null||username.equals("")){
            return ServerResponse.createServerResponse(1,"用户名不能为空");
        }
        return userService.findQuestionByUsername(session,username);

    }






    //注册
    @RequestMapping(value = "/register")
    public ServerResponse<UserInfo> register(String username,String password,String email,
                                             String phone,String question,String answer){

        if(username==null||username.equals("")){

            return ServerResponse.createServerResponse(1,"用户名不能为空");
        }
        if(password==null||password.equals("")){
            return ServerResponse.createServerResponse(2,"密码不能为空");

        }
        if(email==null||email.equals("")){
            return ServerResponse.createServerResponse(3,"邮箱不能为空");

        }
        if(phone==null||phone.equals("")){
            return ServerResponse.createServerResponse(4,"手机号不能为空");

        }
        if(question==null||question.equals("")){
            return ServerResponse.createServerResponse(5,"密保不能为空");

        }
        if(answer==null||answer.equals("")){
            return ServerResponse.createServerResponse(6,"密保答案不能为空");

        }

        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPwd(password);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setQuestion(question);
        userInfo.setAnswer(answer);
        return userService.register(userInfo);

    }

    //登录
    @RequestMapping(value = "/login")
    public ServerResponse<UserInfo> login(String username, String password, HttpSession session,
                                          HttpServletRequest request, HttpServletResponse response)throws SecurityException, IOException, ServletException {
        if(username==null||password==null||username.equals("")||password.equals("")){
            return ServerResponse.createServerResponse(2,"用户名和密码不能为空！");
        }

        ServerResponse<UserInfo> serverResponse=userService.login(username,password);
        UserInfo userInfo=serverResponse.getData();
        if (userInfo!=null){
            //获取ip
            String ip= IpUtils.getRemoteAddress(request);
            //获取mac
            String macAddress=IpUtils.getMACAddress(ip);
            //加密
            String token= MD5Utils.GetMD5Code(macAddress);
            Cookie cookie=new Cookie(Const.AUTOLOGINCOOKIE,token);
            cookie.setMaxAge(3600*24*7);
            response.addCookie(cookie);
            //保存到数据库
            userService.updateTokenById(userInfo.getId(),token);

            session.setAttribute(Const.CURRENTUSER,userInfo);

            return serverResponse;
        }else{
            //用户名或密码错误
            return ServerResponse.createServerResponse(3,"用户名或密码错误！");
        }
    }
}
