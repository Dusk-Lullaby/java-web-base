<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/5
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <th>考试时间</th>
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
                <td>
                    <fmt:formatDate value="${score.examDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        货币类型数字：<fmt:formatNumber value="100" type="currency" />
    </div>
    <div>
        数字格式化：<fmt:formatNumber value="12345.678902" type="number" maxIntegerDigits="4" maxFractionDigits="3" />
    </div>
    <div>
        数字格式化:<fmt:formatNumber value="12345.678902" type="number" pattern="####.##"/>
    </div>
    <div>
        百分比数字：<fmt:formatNumber value="12345.678902" type="percent" maxIntegerDigits="3" maxFractionDigits="2" />
    </div>
</body>
</html>
