<%@ page import="java.util.Arrays" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/8/31
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 设置请求的字符集编码
    request.setCharacterEncoding("UTF-8");
    // 从请求中获取参数username的值
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    // 因为前端传输的数据是一个数组，所以要使用数组来接收
    String[] channels = request.getParameterValues("channel");

    for (String channel : channels) {
        // 在ISO_8859_1这种编码下获取字节数据
        byte[] bytes = channel.getBytes(StandardCharsets.ISO_8859_1);
        // 通过字符串的构造方法进行转码
        String s = new String(bytes, StandardCharsets.UTF_8);
    }
%>
<div>
    <%=username%>
</div>
<div>
    <%=password%>
</div>
<div>
    <%=Arrays.toString(channels)%>
</div>
