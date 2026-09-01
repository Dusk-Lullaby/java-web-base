<%--import属性就是用来导包的--%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/8/22
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第一个JSP程序</title>
</head>
<body>
<div>Hello JvaWeb</div>
<%
    // 这里就是JSP的小脚本，支持编写Java代码
    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = format.format(now);
%>
<%!
    // 这里就是可以定义方法的地方
    String Date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
%>
<div>今天是<%= currentTime %></div>
<div>使用方法展示日期：<%=Date2String(new Date())%></div>
<%
    String[] names = {"张三", "李四", "王五"};
%>
<%
    for (String name : names) {
%>
<div><%=name%></div>
<%
    }
%>
</body>
</html>