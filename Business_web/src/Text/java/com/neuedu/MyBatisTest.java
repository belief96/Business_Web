package com.neuedu;

import com.neuedu.dao.IUserDao;
import com.neuedu.dao.impl.UserDaoMyBatisImpl;
import com.neuedu.pojo.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyBatisTest {

    @Test
    public void testLogin(){

        IUserDao userDao=new UserDaoMyBatisImpl();
        UserInfo userInfo=userDao.login("Bill","cdabb7c05f929ce081e76ba80a731c26");
        System.out.println(userInfo);
    }

    @Test
    public void testRegister(){

        IUserDao userDao=new UserDaoMyBatisImpl();
        // String username, String pwd, String email, String phone, String question, String answer, int role
        UserInfo userInfo=new UserInfo("zhaosi","444","456@qq.com","999","who","me",1);
        System.out.println(userDao.register(userInfo));
    }

    @Test
    public void testCheckUserName(){

        IUserDao userDao=new UserDaoMyBatisImpl();
        System.out.println(userDao.checkUserName("zhaosi"));
    }

    @Test
    public void testfindAll(){
        IUserDao userDao=new UserDaoMyBatisImpl();
        System.out.println(userDao.findAll());
    }

    @Test
    public void testfindByUsername(){
        IUserDao userDao=new UserDaoMyBatisImpl();
        System.out.println(userDao.findByUsername("admin"));
    }

    @Test
    public void testfindByOption(){
        IUserDao userDao=new UserDaoMyBatisImpl();
        UserInfo userInfo=new UserInfo();
        //userInfo.setUsername("admin");
        userInfo.setId(2);
        userInfo.setEmail("666");
        System.out.println(userDao.findByOption(userInfo));
    }

    @Test
    public void testUpdateUser(){
        IUserDao userDao=new UserDaoMyBatisImpl();
        UserInfo userInfo=new UserInfo();
        //userInfo.setUsername("admin");
        userInfo.setId(2);
        userInfo.setEmail("777@qq.com");
        System.out.println(userDao.updateUser(userInfo));
    }

    @Test
    public void testfindByIds(){
        IUserDao userDao=new UserDaoMyBatisImpl();
        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(3);
        System.out.println(userDao.findByIds(ids));
    }
}
