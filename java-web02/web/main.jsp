<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/8/31
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String password = request.getParameter("password");
    String username = request.getParameter("username");
%>
<div>用户名： <%=username%></div>
<div>密码： <%=password%></div>
