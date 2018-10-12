/*
package com.neuedu.controller;

import com.google.gson.Gson;
import com.neuedu.Service.IUserService;
import com.neuedu.Service.impl.UserServiceImpl;
import com.neuedu.businessconst.Const;
import com.neuedu.common.IpUtils;
import com.neuedu.exception.BusinessException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
               }else if(operation.equals("6")){
                   //登录状态下重置密码
                   updatePasswordlogin(request, response);
               }else if(operation.equals("7")){
                   //查看用户信息
                   selectUser(request, response);
               }else if(operation.equals("8")){
                   //退出登录
                   logout(request, response);
               }
    }
    //用户退出登录
    public void logout(HttpServletRequest request,HttpServletResponse response){
        //判断是否登录
         HttpSession session=request.getSession();
        UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
         if(userInfo!=null){
            session.removeAttribute(Const.CURRENTUSER);
         }
           //token清空
       IUserService userService=new UserServiceImpl();
         if(userInfo!=null){
             userService.updateTokenById(userInfo.getId(),"");
         }


    }


    //查看用户信息
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //判断用户是否登录
      HttpSession session=  request.getSession();
       UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
       if(userInfo==null){
           throw BusinessException.createException(session,"你还没有登录呦！！","3s后自动跳转","login.jsp");
       }
        System.out.println("用户信息"+userInfo);

    }
        //登录状态下重置密码
    public void updatePasswordlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String oldpassword=request.getParameter("oldpassword");
        String newpassword=request.getParameter("newpassword");
        HttpSession httpSession=request.getSession();

        if(oldpassword==null||oldpassword.equals("")){
            throw BusinessException.createException(httpSession,"旧密码不能为空","3s后自动跳转","resetpassword.jsp");
        }
        if(newpassword==null||newpassword.equals("")){
            throw BusinessException.createException(httpSession,"新密码不能为空","3s后自动跳转","resetpassword.jsp");
        }

        //调用service层
        IUserService userService=new UserServiceImpl();
        int result=userService.updatePassword(httpSession,oldpassword,newpassword);
        if(result>0){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            throw BusinessException.createException(httpSession,"重置失败","3s后自动跳转","resetpassword.jsp");
        }
    }


    //忘记密码的创建新密码
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String newpassword=request.getParameter("newpassword");
        String token=request.getParameter("token");
        HttpSession httpSession=request.getSession();

        if(username==null||username.equals("")){
            throw BusinessException.createException(httpSession,"用户名不能为空","3s后自动跳转","newpassword.jsp");
        }
        if(newpassword==null||newpassword.equals("")){
            throw BusinessException.createException(httpSession,"密码不能为空","3s后自动跳转","newpassword.jsp");
        }
        if(token==null||token.equals("")){
            throw BusinessException.createException(httpSession,"token不能为空","3s后自动跳转","newpassword.jsp");
        }

        //调用service层
        IUserService userService=new UserServiceImpl();
       int result=userService.updatePassword(httpSession,username,newpassword,token);
        if(result>0){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            throw BusinessException.createException(httpSession,"修改失败","3s后自动跳转","newpassword.jsp");
        }
    }


    //效验答案
    public void checkAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           String username=request.getParameter("username");
           String question=request.getParameter("question");
        String answer=request.getParameter("answer");
        HttpSession httpSession=request.getSession();

        if(username==null||username.equals("")){
            throw BusinessException.createException(httpSession,"用户名不能为空","3s后自动跳转","answer.jsp");
        }
        if(question==null||question.equals("")){
            throw BusinessException.createException(httpSession,"密保不能为空","3s后自动跳转","answer.jsp");
        }
        if(answer==null||answer.equals("")){
            throw BusinessException.createException(httpSession,"答案不能为空","3s后自动跳转","answer.jsp");
        }

        //调用service层
        IUserService userService=new UserServiceImpl();
        String token=userService.chackAnswer(httpSession,username,question,answer);
        httpSession.setAttribute("forget_token",token);
        request.getRequestDispatcher("newpassword.jsp").forward(request,response);
    }


    //查询密保问题
    public void findQuestionByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        HttpSession session=request.getSession();
        //非空验证
        if(username==null||username.equals("")){
           throw BusinessException.createException(session,"用户名不能为空","3s后自动跳转","findPassword.jsp");
        }
       //调用service层
        IUserService userService=new UserServiceImpl();
        String question=userService.findQuestionByUsername(session,username);
        session.setAttribute("question",question);
         request.getRequestDispatcher("answer.jsp").forward(request,response);

    }






    //注册
    public void register(HttpServletRequest request, HttpServletResponse response ){
                 HttpSession session=request.getSession();
        //获取请求参数
        String username=request.getParameter("username");
        String password=request.getParameter("pwd");
        String md5password=MD5Utils.GetMD5Code(password);
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String question=request.getParameter("question");
        String answer=request.getParameter("answer");

        if(username==null||username.equals("")){
            throw BusinessException.createException(session,"用户名不能为空","3s后跳转到注册页面","register.jsp");
        }
        if(md5password==null||md5password.equals("")){
            throw BusinessException.createException(session,"密码不能为空","3s后跳转到注册页面","register.jsp");
        }
        if(email==null||email.equals("")){
            throw BusinessException.createException(session,"邮箱不能为空","3s后跳转到注册页面","register.jsp");
        }
        if(phone==null||phone.equals("")){
            throw BusinessException.createException(session,"手机号不能为空","3s后跳转到注册页面","register.jsp");
        }
        if(question==null||question.equals("")){
            throw BusinessException.createException(session,"密保不能为空","3s后跳转到注册页面","register.jsp");
        }
        if(answer==null||answer.equals("")){
            throw BusinessException.createException(session,"密保答案不能为空","3s后跳转到注册页面","register.jsp");
        }

        IUserService userService=new UserServiceImpl();
        UserInfo userInfo=new UserInfo();
          userInfo.setUsername(username);
          userInfo.setPwd(md5password);
          userInfo.setEmail(email);
          userInfo.setPhone(phone);
          userInfo.setQuestion(question);
          userInfo.setAnswer(answer);
          int result=userService.register(session,userInfo);

          if(result>0){
              throw BusinessException.createException(session,"恭喜！注册成功！","3s后自动跳转到登录","login.jsp");
          }else{
              throw BusinessException.createException(session,"注册失败","3s后自动返回注册页面","register.jsp");
          }

    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response )throws SecurityException, IOException, ServletException {
        HttpSession httpSession=request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("pwd");
        String md5password= MD5Utils.GetMD5Code(password);

        System.out.println("username="+username+"password="+md5password);
        //非空判断
        if(username==null||md5password==null||username.equals("")||md5password.equals("")){
            throw BusinessException.createException(httpSession,"老铁！！用户名和密码不能为空！！！","3秒后自动跳转到登录页面","login.jsp");
        }
        IUserService userService=new UserServiceImpl();
        UserInfo userInfo=userService.login(username,md5password);
        System.out.println(userInfo);
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
            httpSession=request.getSession();

            httpSession.setAttribute(Const.CURRENTUSER,userInfo);
            //将token保存到会话域中
            // httpSession.setAttribute(Const.AUTOLOGINCOOKIE,token);
           // request.getRequestDispatcher("manage/home.jsp").forward(request,response);

            Gson gson=new Gson();
            String json=gson.toJson(userInfo);

           */
/* StringBuffer buffer=new StringBuffer();
            buffer.append("{");
            buffer.append("\"id\"");
            buffer.append(":");
            buffer.append(userInfo.getId());
            buffer.append("}");*//*

              //===============================================================
              //  Access-Control-Allow-Origin'跨域设置俩种方式
           //response.setHeader("Access-Control-Allow-Origin","*");
             String cll= request.getParameter("callback");
              //=================================================================
              //将数据传到页面
            PrintWriter printwriter=response.getWriter();
            //调用客户端js的cll（json）方法
              printwriter.write(cll+"("+json+")");
              //=============================================================
        }else{
            //用户名或密码错误
            throw BusinessException.createException(httpSession,"用户名或密码错误！！","3s后自动跳转到登录页面","login.jsp");

        }
    }
}
*/
