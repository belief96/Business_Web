<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/3
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>管理员页面</title>
</head>
<body>
<h1>欢迎${user.username}登录后台页面</h1>
<a href="/manage/resetpassword.jsp"><button>重置密码</button></a>
<a href="userlogin.jsp"><button>查看个人信息</button></a>
</body>
</html>
