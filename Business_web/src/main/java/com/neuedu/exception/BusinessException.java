package com.neuedu.exception;

import com.neuedu.businessconst.Const;

import javax.servlet.http.HttpSession;

public class BusinessException extends RuntimeException{
    //异常信息
    private String mag;
    //异常提示
    private String warn;
    //跳转的页面URL
    private String url;

    public BusinessException() {

    }

    private BusinessException(String mag,String warn,String url){
        this.mag=mag;
        this.warn=warn;
        this.url=url;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static BusinessException createException(HttpSession session,String mag,String warn,String url){
        BusinessException businessException=new BusinessException(mag,warn,url);
        session.setAttribute(Const.EXCEPTION,businessException);
        return businessException;
    }


}
