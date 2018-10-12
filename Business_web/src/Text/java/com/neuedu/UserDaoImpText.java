package com.neuedu;

import com.neuedu.cache.TokenCache;
import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoImpl;
import com.neuedu.pojo.UserInfo;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserDaoImpText {

    IUserDao userDao;
    @Before
    public  void before(){
        userDao=new UserDaoImpl();
    }

    @Test
    public void textLogin(){
        System.out.println("登录测试");
        UserInfo userInfo= userDao.login("admin","666");
        System.out.println(userInfo);
    }
    @Test
    public void textUpdateTokenById() throws Exception{
        System.out.println("token测试");
        System.out.println(userDao.updateTokenById(1,"ahdkj"));
    }


    @Test
    public void findUesrInfoByToken() throws Exception{
        System.out.println("token测试");
        System.out.println(userDao.findUesrInfoByToken("e88dce2f83a5c0d16dafa2bf5b91a756"));
    }
    @Test
    public void testChackUserName() throws Exception{
        System.out.println("token测试");
        System.out.println(userDao.checkEmail("236732673441"));
    }


    @Test
    public void testfindQuestionByUsername() throws Exception{
        System.out.println("测试");
        System.out.println(userDao.findQuestionByUsername("admin"));
    }

    @Test
    public void testcheckanswer() throws Exception{
        System.out.println("测试");
        System.out.println(userDao.checkanswer("admin","????","myd"));
    }

    @Test
    public void testCache() throws Exception{
        System.out.println("测试");
        TokenCache.set("222333","6666");
        System.out.println(TokenCache.get("222333"));
    }
    @Test
     public void testupdatePassword() throws Exception{
         System.out.println("测试");
         System.out.println(userDao.updatePassword("jkb","123456"));
     }
    @After
    public  void destory(){
        System.out.println("===销毁====");
        userDao=null;
    }

}
