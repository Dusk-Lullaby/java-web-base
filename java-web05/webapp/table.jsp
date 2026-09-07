<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/7
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表格刷新</title>
</head>
<body>
<input type="text" id="region">
<input type="button" value="查询" id="search">
<table>
    <thead>
    <tr>
        <th>代理商ID</th>
        <th>代理商编号</th>
        <th>代理商名称</th>
        <th>代理商区域</th>
    </tr>
    </thead>
    <tbody id="dataBox">

    </tbody>
</table>
</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
    $(function () {
        $("#search").click(function () {
            // 发生GEt方式的请求
            // 使用定位的元素去加载searchAgents这个请求的处理结果
            // $("#dataBox").load("searchAgents?region=" + $("#region").val());
            $.ajax({
                url:"searchAgents",
                type:"get",
                data: {
                    region: $("#region").val()
                },
                success: function (resp) {
                    let tbody = $("#dataBox");
                    // 既然是刷新，那么首先就应该清空tbody中的所有内容
                    tbody.empty();
                    for (let i = 0; i < resp.length; i++) {
                        let tr = $("<tr></tr>");
                        tr.append($("<td>" + resp[i].aid + "</td>"));
                        tr.append($("<td>" + resp[i].ano + "</td>"));
                        tr.append($("<td>" + resp[i].aname + "</td>"));
                        tr.append($("<td>" + resp[i].aregion + "</td>"));
                        tbody.append(tr);
                    }
                }
            })
        })
    })
</script>
</html>
