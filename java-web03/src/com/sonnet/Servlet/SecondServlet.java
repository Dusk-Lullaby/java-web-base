package com.sonnet.Servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class SecondServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        System.out.println("initParameter:");
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String initParameterName = initParameterNames.nextElement();
            String initParameterValue = servletContext.getInitParameter(initParameterName);
            System.out.println(initParameterName + " => " + initParameterValue);
        }
        System.out.println("上下文路径：" + servletContext.getContextPath());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("读取请求头部信息...");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = req.getHeader(headerName);
            System.out.println(headerName + " => " + header);
        }
        System.out.println("========================");
        String contextPath = req.getContextPath(); // 获取上下文路径
        System.out.println("上下文路径：" + contextPath);
        String requestURI = req.getRequestURI(); // 包含上下文路径在内的请求地址
        requestURI = requestURI.replace(contextPath, "");
        System.out.println("当前请求路径：" + requestURI);
        // 这里主要是模拟登录成功，然后将用户名存入session
        // 如果session超时，说明用户没有在页面上进行操作。
        // 这就造成了登录超时，因此登录超时只需要判断，
        // session中是否存在用户名即可。
        // 如果没有说明session超时
        // 新的session中不可能存在用户名
        HttpSession session = req.getSession();
        session.setAttribute("user", "admin");
        System.out.println("=========================");
        System.out.println("开始做出响应");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("请求已处理");
        writer.flush();
        writer.close();
    }
}
