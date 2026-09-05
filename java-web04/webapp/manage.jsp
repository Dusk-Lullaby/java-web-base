<%@ page import="com.sonnet.jsp.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/5
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理页面</title>
</head>
<body>
    <div>
        ${requestScope.user.name} &nbsp;&nbsp; ${requestScope.user["sex"]}
    </div>
    <div>
        ${sessionScope.user.name} &nbsp;&nbsp; ${sessionScope.user["sex"]}
    </div>

    <div>
        ${sessionScope.users[0].name} &nbsp;&nbsp; ${sessionScope.users[0]["sex"]}
    </div>
    <div>
        ${sessionScope.users[1].name} &nbsp;&nbsp; ${sessionScope.users[1]["sex"]}
    </div>

    <div>
        ${sessionScope.data.admin}
    </div>
    <div>
        ${sessionScope.data.test}
    </div>
</body>
</html>
