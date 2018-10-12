<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/3
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h1 style="font-size: 25px;margin: 10px 100px;">欢迎登录</h1>
<div style="margin: 10px 100px;">
   <form action="user?operation=1" method="post">
       用户名<input type="text" name="username">
       <p></p>
       密   码<input type="password" name="pwd">
       <p></p>
       <input type="submit" value="提交">
   </form>
<a href="findPassword.jsp"><button>忘记密码</button></a>
</div>
</body>
</html>
