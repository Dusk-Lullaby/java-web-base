<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/8/31
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>内置对象request</title>
</head>
<body>
    <%--post请求发送的参数如果是中文，那么在页面展示的时候可能会出现乱码，可以在request对象中设置请求编码的格式，然后在从request对象中取值--%>
    <%--如果get请求发送的是中文，那么在页面展示的时候也可能出现乱码，可以使用字符串的转码方式来解决--%>
    <form action="info.jsp" method="post">
        <div>
            用户名
            <input type="text" name="username">
        </div>
        <div>
            密码
            <input type="password" name="password">
        </div>
        <div>
            信息来源
            <input type="checkbox" name="channel" value="报刊">报刊
            <input type="checkbox" name="channel" value="网络">网络
            <input type="checkbox" name="channel" value="朋友推荐">朋友推荐
            <input type="checkbox" name="channel" value="其他">其他
        </div>
        <div>
            <input type="submit" value="注册">
            <input type="reset" value="重置">
        </div>
    </form>
</body>
</html>
