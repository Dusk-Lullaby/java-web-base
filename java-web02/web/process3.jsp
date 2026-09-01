<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/1
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数username的值
    String username = request.getParameter("username");
    // 获取参数password的值
    String password = request.getParameter("password");
    String rememberMe = request.getParameter("rememberMe");
    if ("admin".equals(username) && "123456".equals(password)) {
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        // 只有登录成功的情况且勾选记住密码才会记住密码
        if (!"on".equals(rememberMe)) {
            username = "";
            password = "";
            rememberMe = "";
        }
        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        Cookie rememberMeCookie = new Cookie("rememberMe", rememberMe);
        // 记住密码是属于服务器端对用户端操作的一种响应。这个响应就是使用cookie来存储账号和密码
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        response.addCookie(rememberMeCookie);

        // 页面重定向至主页面
        response.sendRedirect("main2.jsp");
    }
%>
