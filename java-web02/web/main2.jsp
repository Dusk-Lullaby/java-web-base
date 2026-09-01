<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/1
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="timeout.jsp"%>
<%
    String password = (String) session.getAttribute("password");
    String username = (String) session.getAttribute("username");
    String sessionID = session.getId();
%>
<div>response数据类型： <%=response.getClass().getName()%></div>
<div>session的数据类型： <%=session.getClass().getName()%></div>
<div>sessionID： <%=sessionID%></div>
<div>用户名： <%=username%></div>
<div>密码： <%=password%></div>
