<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/1
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>response内置对象</title>
</head>
<%
    String username = "";
    String password = "";
    boolean rememberMe = false;
    // 从请求中获取cookie的信息
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("username".equals(name)) {
                username = cookie.getValue();
            } else if ("password".equals(name)) {
                password = cookie.getValue();
            } else if ("rememberMe".equals(name)) {
                rememberMe = cookie.getValue().equals("on");
            }
        }
    }
%>
<body>
<form action="process3.jsp" method="post">
    <div><span>用户名</span><input type="text" name="username" value="<%=username%>"></div>
    <div><span>密码</span><input type="password" name="password" value="<%=password%>"></div>
    <div><input type="checkbox" name="rememberMe" <%=rememberMe ? "checked" : ""%>>记住密码</div>
    <div><input type="submit" value="登录"></div>
</form>
</body>
</html>
