<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/4
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${ex.mag}</h1>
<span id="warn">${ex.warn}</span>
<script>

    window.onload=function() {
        var count=3;
        window.setInterval(function(){
            count--;
            if (count==0){
                window.location.href="${ex.url}";
                //结束方法的运行
              window.clearInterval();
            }

            document.getElementById("warn").innerHTML=count+"s后页面自动跳转";
        },1000);
    }
</script>

</body>
</html>
