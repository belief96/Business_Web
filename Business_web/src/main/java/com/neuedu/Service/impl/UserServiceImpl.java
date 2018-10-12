package com.neuedu.Service.impl;

import com.mysql.fabric.Server;
import com.neuedu.Service.IUserService;
import com.neuedu.businessconst.Const;
import com.neuedu.cache.TokenCache;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.dao.impl.UserDaoMyBatisImpl;
import com.neuedu.exception.BusinessException;
import com.neuedu.pojo.UserInfo;
import com.neuedu.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService{
    IUserDao iUserDao=new UserDaoImpl();



    @Override
    public ServerResponse<UserInfo> login(String username, String pwd) {

       UserInfo userInfo= iUserDao.login(username, MD5Utils.GetMD5Code(pwd));
       if(userInfo!=null){
           return ServerResponse.createSercerResponse(0,userInfo);
       }else{
           return ServerResponse.createServerResponse(1,"登录失败!",userInfo);
       }
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
    public ServerResponse<UserInfo> register(UserInfo userInfo) {

        //判断username是否存在
        int result_username=iUserDao.checkUserName(userInfo.getUsername());
        if(result_username>0){
            return ServerResponse.createServerResponse(7,"用户名已存在");
        }

        // /判断邮箱是否存在
        int result_email=iUserDao.checkEmail(userInfo.getEmail());
        if(result_email>0){
            return ServerResponse.createServerResponse(7,"邮箱已存在");
        }
        //密码加密
        userInfo.setPwd(MD5Utils.GetMD5Code(userInfo.getPwd()));
        //设置角色
        userInfo.setRole(1);//可以使用枚举定义
        //注册
        int result=iUserDao.register(userInfo);
        if(result>0){
            return ServerResponse.createServerResponse(0,"注册成功！");
        }else {
            return ServerResponse.createServerResponse(8,"注册失败!");
        }
    }

    @Override
    public ServerResponse<String> findQuestionByUsername(HttpSession session, String username) {

        //验证username的存在
        int result= iUserDao.checkUserName(username);
        if(result<=0){
            return ServerResponse.createServerResponse(2,"用户名不存在");
        }

        //用户名存在--查询问题
        String question= iUserDao.findQuestionByUsername(username);

        return ServerResponse.createServerResponse(0,"获取问题成功！",question);
    }

    @Override
    public ServerResponse<String> chackAnswer(String username, String question, String answer) {

        //判断非空username
        int result=iUserDao.checkUserName(username);
        if(result<=0){
            return ServerResponse.createServerResponse(4,"用户名不存在!");
        }
        //效验
        int count=iUserDao.checkanswer(username, question, answer);
        if(count>0){
            //效验成功
            String forget_token= UUID.randomUUID().toString();
            //缓存
            TokenCache.set(Const.TOKEN_PREFIX+username,forget_token);

            return ServerResponse.createServerResponse(0,"校验成功",forget_token);
        }else {
            return ServerResponse.createServerResponse(5,"答案错误！");
        }





    }

    @Override
    public ServerResponse<String> updatePassword(String username, String newpassword, String token) {
        //判断非空username
        int result=iUserDao.checkUserName(username);
        if(result<=0){
            return ServerResponse.createServerResponse(4,"用户名不存在");
        }
        //判断token是否有效
        String cacheToken=TokenCache.get(Const.TOKEN_PREFIX+username);
        if(!cacheToken.equals(token)){
            return ServerResponse.createServerResponse(5,"无效的token");
        }
        //修改密码
        result= iUserDao.updatePassword(username,newpassword);

        if(result>0){
            return ServerResponse.createServerResponse(0,"恭喜，修改成功！");
        }else {
            return ServerResponse.createServerResponse(6,"修改失败！");
        }
    }

    @Override
    public ServerResponse<UserInfo> updatePassword(String oldpassword, String newpassword,UserInfo userInfo) {

        //获取用户的旧密码

        UserInfo olduser= iUserDao.login(userInfo.getUsername(),MD5Utils.GetMD5Code(oldpassword));
        if(olduser==null){
            //throw BusinessException.createException(session,"密码验证错误，请重新输入","3s后自动跳转","home.jsp");
            return ServerResponse.createServerResponse(6,"旧密码错误");
        }
        //重置密码
        int result= iUserDao.updatePassword(userInfo.getUsername(),MD5Utils.GetMD5Code(newpassword));
        if(result>0){
            return ServerResponse.createServerResponse(0,"修改成功");
        }else {
            return ServerResponse.createServerResponse(7,"修改失败");

        }
    }

    @Override
    public boolean isAdminRole(UserInfo userInfo) {
        return userInfo.getRole()==0;
    }

}
