<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/6
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
</head>
<body>
<form action="user?operation=6" method="post">
    用户名<input type="text" name="username">
    <br>
    旧密码<<input type="password" name="oldpassword">
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
