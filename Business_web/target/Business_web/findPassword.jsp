<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/5
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
<h1 style="font-size: 25px;margin: 10px 100px;">找回密码页面</h1>
<form action="user?operation=3" method="post">
    已有用户名<input type="text" name="username">
    <br>
    <input type="submit" value="提交">

</form>
</body>
</html>
