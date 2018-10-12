<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/4
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="font-size: 25px;margin: 10px 100px;">欢迎进入注册页面</h1>
<div style="margin: 10px 100px;">
<form action="user?operation=2" method="post">
   用户名       <input type="text" name="username">
    <br>
 <br>
    密   码     <input type="text" name="pwd">
    <br>
 <br>
    邮   箱     <input type="text" name="email">
 <br>
 <br>
    电话号      <input type="text" name="phone">
 <br>
 <br>
    密   保     <input type="text" name="question">
 <br>
 <br>
    答   案     <input type="text" name="answer">
 <br>
 <br>
                <input type="submit" value="立即注册">
</form>
    </div>

</body>
</html>
