package com.neuedu.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable{

    private int id;
    private String username;
    private String pwd;
    private String email;
    private String phone;
    private String question;//找回密码的问题
    private String answer;//找回密码的答案
    private int role;//角色---管理员、用户
    private long create_time;//创建时间
    private long update_time;//最后一次更新时间


    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", role=" + role +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public UserInfo() {

    }

    public UserInfo(String username, String pwd, String email, String phone, String question, String answer, int role) {
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }
}
