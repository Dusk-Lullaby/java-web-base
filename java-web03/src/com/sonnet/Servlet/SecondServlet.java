package com.sonnet.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class SecondServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("读取请求头部信息...");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = req.getHeader(headerName);
            System.out.println(headerName + " => " + header);
            System.out.println("========================");
            String contextPath = req.getContextPath(); // 获取上下文路径
            System.out.println("上下文路径：" + contextPath);
            String requestURI = req.getRequestURI(); // 包含上下文路径在内的请求地址
            requestURI = requestURI.replace(contextPath, "");
            System.out.println("当前请求路径：" + requestURI);

            System.out.println("=========================");
            System.out.println("开始做出响应");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=uft-8");
            PrintWriter writer = resp.getWriter();
            writer.println("请求已处理");
            writer.flush();
            writer.close();
        }
    }
}
