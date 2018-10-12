package com.neuedu.Service.impl;

import com.neuedu.Service.IUserService;
import com.neuedu.businessconst.Const;
import com.neuedu.cache.TokenCache;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.exception.BusinessException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.MD5Utils;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class UserServiceImpl implements IUserService{
    IUserDao iUserDao=new UserDaoImpl();


    @Override
    public UserInfo login(String username, String pwd) {


        return  iUserDao.login(username, pwd);
    }

    @Override
    public int updateTokenById(int userid, String token) {

        return iUserDao.updateTokenById(userid, token);
    }

    @Override
    public UserInfo findUesrInfoByToken(String token) {

        return iUserDao.findUesrInfoByToken(token);
    }

    @Override
    public int register(HttpSession session, UserInfo userInfo) {

        //判断username是否存在
          int result_username=iUserDao.checkUserName(userInfo.getUsername());
          if(result_username>0){
              throw BusinessException.createException(session,"用户名已经存在了","3s后跳转到注册页面","register.jsp");
          }

        // /判断邮箱是否存在
        int result_email=iUserDao.checkEmail(userInfo.getEmail());
        if(result_email>0){
            throw BusinessException.createException(session,"邮箱已经存在了","3s后跳转到zhu册页面","register.jsp");
        }
        //密码加密
       userInfo.setPwd(MD5Utils.GetMD5Code(userInfo.getPwd()));
        //设置角色
        userInfo.setRole(1);//可以使用枚举定义
         //注册
        int result=iUserDao.register(userInfo);
        return result;
    }

    @Override
    public String findQuestionByUsername(HttpSession session, String username) {

       //验证username的存在
        int result= iUserDao.checkUserName(username);
        if(result<=0){
            throw BusinessException.createException(session,"用户名不存在","3s后自动返回","findPassword.jsp");
        }

        //用户名存在--查询问题
      return iUserDao.findQuestionByUsername(username);
    }

    @Override
    public String chackAnswer(HttpSession session, String username, String question, String answer) {

       //判断非空username
        int result=iUserDao.checkUserName(username);
        if(result<=0){
            throw BusinessException.createException(session,"用户名不存在","3s后自动跳转","answer.jsp");
        }
        //效验
       int count=iUserDao.checkanswer(username, question, answer);
       if(count>0){
           //效验成功
           String forget_token= UUID.randomUUID().toString();
         //缓存
           TokenCache.set(Const.TOKEN_PREFIX+username,forget_token);

             return forget_token;
       }else {
           throw BusinessException.createException(session,"输入答案不匹配","3s后自动跳转","answer.jsp");
       }



    }

    @Override
    public int updatePassword(HttpSession session,String username, String newpassword, String token) {
        //判断非空username
        int result=iUserDao.checkUserName(username);
        if(result<=0){
            throw BusinessException.createException(session,"用户名不存在","3s后自动跳转","newpassword.jsp");
        }
      //判断token是否有效
        String cacheToken=TokenCache.get(Const.TOKEN_PREFIX+username);
      if(!cacheToken.equals(token)){
          throw BusinessException.createException(session,"无效的token","3s后自动跳转","newpassword.jsp");
      }
      //修改密码
        return iUserDao.updatePassword(username,newpassword);
    }

    @Override
    public int updatePassword(HttpSession session, String oldpassword, String newpassword) {
      //判断用户是否登录
            UserInfo userInfo=(UserInfo)session.getAttribute(Const.CURRENTUSER);
         if(userInfo==null){
             throw BusinessException.createException(session,"你还没有登录，请先登录","3s后自动跳转","login.jsp");
         }
         //获取用户的旧密码

        UserInfo olduser= iUserDao.login(userInfo.getUsername(),MD5Utils.GetMD5Code(oldpassword));
         if(olduser==null){
            throw BusinessException.createException(session,"密码验证错误，请重新输入","3s后自动跳转","home.jsp");
         }
       //重置密码
        return  iUserDao.updatePassword(userInfo.getUsername(),MD5Utils.GetMD5Code(newpassword));

    }

}
