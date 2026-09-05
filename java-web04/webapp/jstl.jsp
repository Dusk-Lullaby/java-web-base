<%@ page import="com.sonnet.jsp.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/5
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>jstl</title>
</head>
<body>
<%
    User user = new User();
%>
    <div>
        <%--这相当于在页面创建了一个名为test的变量--%>
        <c:set var="test" value="测试" scope="page" />
        <c:set target="<%=user%>" value="管理员" property="name"/>
    </div>

    <div>
        页面范围内的变量：${pageScope.test}
    </div>
    <div>
        <%=user.getName()%>
    </div>

    <%--移除页面范围内的test变量--%>
    <c:remove var="test" scope="page"/>
    <div>
        页面范围内的变量：${pageScope.test}
    </div>

</body>
</html>
