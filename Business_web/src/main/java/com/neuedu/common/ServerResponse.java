package com.neuedu.common;

import com.google.gson.Gson;
import com.neuedu.pojo.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerResponse<T> {

    private int status; //状态码
    private T data;
    private String msg;


    private ServerResponse() {
    }

    private ServerResponse(int status) {
        this.status = status;
    }


    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;

    }

    private ServerResponse(int status, String msg ) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }

    //创建服务端相应对象
    public static <T> ServerResponse<T> createServerResponse(int status) {
        return new ServerResponse<T>(status);
    }


    public static <T> ServerResponse<T> createSercerResponse(int status,T data){
        return new ServerResponse<T>(status, data);
    }

    public static <T> ServerResponse<T> createServerResponse(int status, String msg) {
        return new ServerResponse<T>(status, msg);
    }



    public static <T> ServerResponse<T> createServerResponse(int status, String msg, T data) {
        return new ServerResponse<T>(status, msg, data);
    }

    public static void convertToJson(ServerResponse serverResponse, HttpServletResponse response) throws IOException {
        Gson gson=new Gson();
        String json=gson.toJson(serverResponse);


        PrintWriter printWriter=response.getWriter();
        printWriter.print(json);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

