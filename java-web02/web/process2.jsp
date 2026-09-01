
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取参数username的值
    String username = request.getParameter("username");
    // 获取参数password的值
    String password = request.getParameter("password");
    if ("admin".equals(username) && "123456".equals(password)) {
        // 将用户名和密码存储在session中，因为session是针对用户来的，因此只有用户本人，可以获取存储的数据
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        // 设置session的超时时间为5秒
        session.setMaxInactiveInterval(5);
        // 页面重定向至主页面
        response.sendRedirect("main2.jsp");
    }
%>
