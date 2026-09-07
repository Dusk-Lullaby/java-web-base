<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/7
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:forEach items="${agents}" var="agent" >
    <tr>
        <td>${agent.aid}</td>
        <td>${agent.ano}</td>
        <td>${agent.aname}</td>
        <td>${agent.aregion}</td>
    </tr>
</c:forEach>