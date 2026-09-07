<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/6
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax登录</title>
</head>
<body>
    <form action="login" method="post">
        <div>
            <input type="text" name="username" id="username">
        </div>
        <div>
            <input type="password" name="password" id="password">
        </div>
        <div>
            <input type="button" value="登录" id="loginBtn">
        </div>
    </form>
</body>
<%--<script type="text/javascript" src="js/ajax.js"></script>--%>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">

    $(function () {
        $("#loginBtn").click(function () {
           $.ajax({
               url: "login",
               type: "post",
               contentType: "application/x-www-form-urlencoded;charset=UTF-8",
               data: {
                   username: $("#username").val(),
                   password: $("#password").val()
               },
               success: function (resp) {
                   if (resp === "3") {
                       alert("账号密码错误");
                   } else {
                       alert("登录成功");
                   }
               }
           })
        });
    })

    // document.getElementById("loginBtn").onclick = function () {
    //     let username = document.getElementById("username").value;
    //     let password = document.getElementById("password").value;
    //
    //     ajax({
    //         url: "login",
    //         method: "post",
    //         contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    //         data: {username:username,password:password},
    //         success: function (resp) {
    //             if (resp === "3") {
    //                 alert("账号密码错误");
    //             } else {
    //                 alert("登录成功");
    //             }
    //         }
    //     })

        // let xmlHttpRequest;
        // // 检测window中是否存在ActiveXObject这个对象
        // // 微软的IE需要这种方式来获取Ajax核心对象
        // if (window.ActiveXObject) {
        //     xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        // } else {
        //     xmlHttpRequest = new XMLHttpRequest();
        // }
        // xmlHttpRequest.onreadystatechange = function () {
        //     if (xmlHttpRequest.readyState === 4) {
        //         if (xmlHttpRequest.status === 200) {
        //             let result = xmlHttpRequest.responseText;
        //             if (result === "3") {
        //                 alert("账号密码错误");
        //             } else if (result==="4") {
        //                 alert("登录成功");
        //             } else {
        //                 alert("1111")
        //             }
        //         }
        //     }
        // };
        // xmlHttpRequest.open("post", "login", true);
        // // POST 方式发送请求时，携带数据一定要请求头，设置传输数据的类型为
        // // application/x-www-form-urlencoded其作用将键值对的参数用&连接起来
        // // 如果有空格，如果有空格将空格转换为+加号，将特殊符号转化为ASCII HEX值
        // xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
        // // post请求发送数据需要在send方法中
        // // 使用 encodeURIComponent() 可以正确处理中文、空格、&、= 等特殊字符。
        // xmlHttpRequest.send("username=" + encodeURIComponent(username) + "&password=" + encodeURIComponent(password))
    // }
</script>
</html>
