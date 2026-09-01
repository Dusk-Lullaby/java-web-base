<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/1
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
     String user = (String) session.getAttribute("username");
    // 说明登录超时，因为session超时，session就会被回收掉，那么session里面存储的数据就没有了
    // 用户又发起了请求，此时服务器发现浏览器传输过来的JSESSIONID不存在，就重新为用户创建一个新的session
    // 既然是新的session，当然没有之前存储的数据，如此呢来判断session已经超时
    if (user == null) {
        response.sendRedirect("login2.jsp");
    }
%>
