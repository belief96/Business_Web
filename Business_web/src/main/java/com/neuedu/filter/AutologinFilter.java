package com.neuedu.filter;

import com.neuedu.businessconst.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.Service.IUserService;
import com.neuedu.Service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/manage/*")
public class AutologinFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = null;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession httpSession = httpServletRequest.getSession();
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(Const.USERNAMECOOKIE);
        if (userInfo != null) {
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieName = c.getName();
                if (cookieName.equals(Const.AUTOLOGINCOOKIE)) {
                    token = c.getValue();
                }
            }
        }

        if (token != null) {
            IUserService userService = new UserServiceImpl();
            userInfo = userService.findUesrInfoByToken(token);
            if (userInfo != null) {//登陆成功
                HttpSession session = ((HttpServletRequest) req).getSession();
                session.setAttribute(Const.CURRENTUSER, userInfo);
                chain.doFilter(req, resp);
                return;
            }
        }

        //httpServletResponse.sendRedirect("http://localhost:8080/Business_web/login.jsp");
        //代码重构 code review
        ServerResponse serverResponse= ServerResponse.createServerResponse(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        ServerResponse.convertToJson(serverResponse,httpServletResponse);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
