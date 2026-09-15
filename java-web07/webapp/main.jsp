<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/11
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>
    <input type="button" value="查询" id="searchBtn">
    <input type="button" value="修改" id="updateBtn">
</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
    $(function () {
        $("#searchBtn").click(function () {
            $.ajax( {
                url:"search",
                type: "get",
                data: {},
                success: function (resp) {
                    console.log(resp);
                }
            })
        })
    })

    $(function () {
        $("#updateBtn").click(function () {
            $.ajax( {
                url:"update",
                type: "post",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                data: {
                    id: 3,
                    name: "王五",
                    sex: "女",
                    age: 30
                },
                success: function (resp) {
                    alert(resp);
                }
            })
        })
    })
</script>
</html>
