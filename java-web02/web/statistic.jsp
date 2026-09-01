<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>application内置对象</title>
</head>
<%
    Integer count = (Integer) application.getAttribute("count");
    if (count == null) {
        // 表示这是第一个用户访问
        count = 1;
    } else {
        count++;
    }
    application.setAttribute("count", count);
%>
<body>
    <div><%=application.getClass().getName()%></div>
    <div>网站访问次数：<%=count%></div>
</body>
</html>
