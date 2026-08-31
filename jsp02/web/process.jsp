<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/8/31
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数username的值
    String username = request.getParameter("username");
    // 获取参数password的值
    String password = request.getParameter("password");
    if ("admin".equals(username) && "123456".equals(password)) {
        // 页面重定向至主页面
//        response.sendRedirect("main.jsp");
        // 从请求中获取一个转发的对象，既然是请求转发，那么上一次请求的信息，准发的对象应该也清楚，因此可以从新的对象中获取上一次的参数
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        // 实现请求转发
        dispatcher.forward(request, response);
    }
%>
