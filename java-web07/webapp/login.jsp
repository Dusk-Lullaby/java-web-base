<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/11
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <input type="text" id="username">
    <input type="text" id="password">
    <input type="button" value="查询" id="searchBtn">
</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
    $(function () {
        $("#searchBtn").click(function () {
            $.ajax( {
                url:"login",
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                data: {
                    username: $("#username").val(),
                    password: $("#password").val()
                },
                success: function (resp) {
                    if (resp === "1") {
                        window.location.href = "main.jsp";
                    } else if (resp == "-1") {
                        alert("账号不存在");
                    } else {
                        alert("账号或密码错误");
                    }
                }
            })
        })
    })
</script>
</html>
