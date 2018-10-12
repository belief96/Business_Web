package com.neuedu.dao.impl;

import com.neuedu.common.JDBCUTILS;
import com.neuedu.dao.IUserDao;
import com.neuedu.pojo.UserInfo;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public UserInfo login(String username, String pwd) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
           String sql="select id,username,pwd,email,phone,question,answer,role from _User where username=? and pwd=?";
             preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pwd);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){

                int userid=resultSet.getInt("id");
                String _username=resultSet.getString("username");
                String _password=resultSet.getString("pwd");
                String _email=resultSet.getString("email");
                String _phone=resultSet.getString("phone");
                String _question=resultSet.getString("question");
                String _answer=resultSet.getString("answer");
                int role=resultSet.getInt("role");

                UserInfo userInfo=new UserInfo();
                userInfo.setId(userid);
                userInfo.setUsername(_username);
                userInfo.setPwd(_password);
                userInfo.setEmail(_email);
                userInfo.setPhone(_phone);
                userInfo.setQuestion(_question);
                userInfo.setAnswer(_answer);
                userInfo.setRole(role);
                return userInfo;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<UserInfo> findAll() {
        return null;
    }

    //修改token
    @Override
    public int updateTokenById(int userid, String token) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="update _user set token=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值

            preparedStatement.setString(1,token);
            preparedStatement.setInt(2,userid);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public UserInfo findUesrInfoByToken(String token) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="select id,username,pwd,email,phone,question,answer,role from _User where token=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,token);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){
                int userid=resultSet.getInt("id");
                String _username=resultSet.getString("username");
                String _password=resultSet.getString("pwd");
                String _email=resultSet.getString("email");
                String _phone=resultSet.getString("phone");
                String _question=resultSet.getString("question");
                String _answer=resultSet.getString("answer");
                int role=resultSet.getInt("role");

                UserInfo userInfo=new UserInfo();
                userInfo.setId(userid);
                userInfo.setUsername(_username);
                userInfo.setPwd(_password);
                userInfo.setEmail(_email);
                userInfo.setPhone(_phone);
                userInfo.setQuestion(_question);
                userInfo.setAnswer(_answer);
                userInfo.setRole(role);
                return userInfo;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public int checkUserName(String username) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="select count(1) from _User where username=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,username);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){

                int count=resultSet.getInt(1);

                return count;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int checkEmail(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="select count(1) from _User where email=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,email);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){

                int count=resultSet.getInt(1);

                return count;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int register(UserInfo userInfo) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="insert into _user (username,pwd,email,phone,question,answer,role,create_time,update_time) value (?,?,?,?,?,?,?,now(),now())";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值

            preparedStatement.setString(1,userInfo.getUsername());
            preparedStatement.setString(2,userInfo.getPwd());
            preparedStatement.setString(3,userInfo.getEmail());
            preparedStatement.setString(4,userInfo.getPhone());
            preparedStatement.setString(5,userInfo.getQuestion());
            preparedStatement.setString(6,userInfo.getAnswer());
            preparedStatement.setInt(7,userInfo.getRole());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String findQuestionByUsername(String username) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="select question from _User where username=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,username);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){

                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int checkanswer(String username, String question, String answer) {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="select count(1) from _User where username=? and question=? and answer=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,question);
            preparedStatement.setString(3,answer);
            //执行查询，返回结果集
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first()){

                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int updatePassword(String username, String newPassword) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=JDBCUTILS.getConnection();
            String sql="update _user set pwd=? where username=?";
            preparedStatement=connection.prepareStatement(sql);
            //占位符赋值

            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,username);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUTILS.close(connection,preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return 0;
    }

    @Override
    public List<UserInfo> findByUsername(String username) {
        return null;
    }

    @Override
    public UserInfo findByOption(UserInfo userInfo) {
        return null;
    }

    @Override
    public int updateUser(UserInfo userInfo) {
        return 0;
    }

    @Override
    public List<UserInfo> findByIds(List<Integer> ids) {
        return null;
    }

    /*@Override
    public int insertUsers(List<UserInfo> userInfoList) {
        return 0;
    }*/
}
