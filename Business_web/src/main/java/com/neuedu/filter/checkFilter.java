package com.neuedu.filter;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/manage/*")
public class checkFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpServletResponse=(HttpServletResponse)resp;
        HttpServletRequest httpServletRequest=(HttpServletRequest)req;
        HttpSession httpSession=httpServletRequest.getSession();
        Object object=httpSession.getAttribute(Const.CURRENTUSER);
        if(object!=null&&object instanceof UserInfo){
            chain.doFilter(req, resp);
        }else {
            ServerResponse serverResponse= ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
            ServerResponse.convertToJson(serverResponse,httpServletResponse);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
