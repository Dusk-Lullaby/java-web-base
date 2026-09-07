<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/6
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
    <form action="checkUsername" method="post">
        <span>用户名：</span>
        <input type="text" name="username" id="username">
        <span style="color: red" id="tip"></span>
    </form>
</body>
<%--<script type="text/javascript" src="js/ajax.js"></script>--%>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">

    $(function () {
        $("#username").blur(function () {
            $.ajax({
                url: "checkUsername",
                type: "get",
                data: {
                    username: $("#username").val()
                },
                success: function (resp) {
                    let tip = $("#tip");
                    // 这里需要对结果进行处理
                    if (resp === "1") { // 用户名已经存在
                        tip.text("该账号已经被注册");
                        tip.css("color","red");
                    } else {
                        tip.text("该账号可以注册");
                        tip.css("color", "green");
                    }
                }
            })
        })
    })

    // let element = document.getElementById("username");
    // // 为username元素添加一个失去焦点的事件
    // element.onblur = function () {
    //     let value = element.value;
    //     if (value !== "") {
    //         ajax({
    //             url: "checkUsername",
    //             method: "get",
    //             data: {
    //                 username: value
    //             },
    //             success: function (resp) {
    //                 let tip = document.getElementById("tip");
    //                 // 这里需要对结果进行处理
    //                 if (resp === "1") { // 用户名已经存在
    //                     tip.innerText = "该账号已经被注册";
    //                     tip.style.color = "red";
    //                 } else {
    //                     tip.innerText = "该账号可以注册";
    //                     tip.style.color = "green";
    //                 }
    //             }
    //         })

            // let xmlHttpRequest;
            // if (window.ActiveXObject) { // 检测window中是否存在ActiveXObject这个对象
            //     // 微软的IE需要这种方式来获取Ajax核心对象
            //     xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP")
            // } else {
            //     xmlHttpRequest = new XMLHttpRequest();
            // }
            // xmlHttpRequest.onreadystatechange = function () {
            //     // 就绪状态为4的时候表示已经将服务器传输回来
            //     if (xmlHttpRequest.readyState === 4) {
            //         // HTTP状态码为200的时候，说明该请求处理成功
            //         if (xmlHttpRequest.status === 200) {
            //             let tip = document.getElementById("tip");
            //             let responseText = xmlHttpRequest.responseText;
            //             // 这里需要对结果进行处理
            //             if (responseText === "1") { // 用户名已经存在
            //                 tip.innerText = "该账号已经被注册";
            //                 tip.style.color = "red";
            //             } else {
            //                 tip.innerText = "该账号可以注册";
            //                 tip.style.color = "green";
            //             }
            //         }
            //     }
            // }
            // // GET请求发送数据的方式是在URL地址后面进行数据的拼接
            // // true表示异步，false表示同步
            // xmlHttpRequest.open("get", "checkUsername?username=" + value, true)
            // xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
            //
            // // 这个表示发送数据，GET请求方式直接为空即可
            // xmlHttpRequest.send();
    //     }
    // }
</script>
</html>
