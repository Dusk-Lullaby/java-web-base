<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/5
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>成绩信息展示</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <c:if test="${sessionScope.zhangsan.score > 80}" var="result" scope="request">
        <div>成绩高于80</div>
    </c:if>
    <div>成绩高于80吗? ${requestScope.result}</div>


    <c:choose>
        <c:when test="${sessionScope.zhangsan.score > 80}">
            <div>成绩良好</div>
        </c:when>
        <c:when test="${sessionScope.zhangsan.score > 70}">
            <div>成绩中等</div>
        </c:when>
        <c:otherwise>
            <div>成绩较差</div>
        </c:otherwise>
    </c:choose>

    <table>
        <thead>
        <tr>
            <th>姓名</th>
            <th>成绩</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.scores}" var="score" begin="2" step="3" end="14">
            <tr>
                <td>
                    ${score.name}
                </td>
                <td>
                    ${score.score}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
