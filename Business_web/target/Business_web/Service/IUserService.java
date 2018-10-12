package com.neuedu.Service;

import com.neuedu.pojo.UserInfo;

import javax.servlet.http.HttpSession;

public interface IUserService {

    //登录
    public UserInfo login(String username, String pwd);

    //修改token
    public int updateTokenById(int userid, String token);

    //根据token查询用户信息
    public UserInfo findUesrInfoByToken(String token);


    public int register(HttpSession session, UserInfo userInfo);


    public String findQuestionByUsername(HttpSession session,String username);

     //效验答案
    public String chackAnswer(HttpSession session,String username,String question,String answer);

    //忘记密码
    public int updatePassword(HttpSession session ,String username,String newpassword,String token);
//登录状态下的重置密码

public int updatePassword(HttpSession session,String oldpassword,String newpassword);
}