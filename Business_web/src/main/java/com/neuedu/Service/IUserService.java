package com.neuedu.Service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

import javax.servlet.http.HttpSession;

public interface IUserService {

    //登录
    public ServerResponse<UserInfo> login(String username, String pwd);

    //修改token
    public int updateTokenById(int userid, String token);

    //根据token查询用户信息
    public UserInfo findUesrInfoByToken(String token);


    //注册
    public ServerResponse<UserInfo> register(UserInfo userInfo);



    public ServerResponse<String> findQuestionByUsername(HttpSession session,String username);

    //效验答案
    public ServerResponse<String> chackAnswer(String username,String question,String answer);

    //忘记密码
    public ServerResponse<String> updatePassword(String username,String newpassword,String token);

    //登录状态下的重置密码
    public ServerResponse<UserInfo> updatePassword(String oldpassword,String newpassword,UserInfo userInfo);

    //检测用户管理员权限
    public boolean isAdminRole(UserInfo userInfo);

}
