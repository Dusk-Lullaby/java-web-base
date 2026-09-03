package com.sonnet.Servlet;

import jakarta.servlet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class FirstServlet implements Servlet {

    private ServletConfig servletConfig;

    // Servlet 的实例在该Servlet处理的第一次请求的时候才会创建，创建之后立刻调用初始化方法，完成Servlet初始化
    public FirstServlet() {
        System.out.println("create Servlet instance");
    }

    // Servlet初始化，只有初始化完成的Servlet才能提供处理请求的服务
    // init方法在该Servlet对象第一次处理请求的时候才调用
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        // 获取Servlet配置的所有参数名称
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            // 获取下一个参数名
            // 即便是第一个元素，也必须先执行 nextElement()，
            // 因为集合在刚初始化完成时，游标处于第一个元素之前（可以理解为停留在位置 -1）。
            String parameterName = initParameterNames.nextElement();
            // 获取给定参数名称的参数值
            String parameterValue = servletConfig.getInitParameter(parameterName);
            System.out.println(parameterName + " -> " + parameterValue);
        }
        System.out.println("Servlet init complete");
    }

    // 获取Servlet配置
    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    // 处理请求的服务方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet handle request and response");
        servletResponse.setContentType("text/html;charset=UTF-8");
        // 从请求中获取字符集编码
        String characterEncoding = servletRequest.getCharacterEncoding();
        System.out.println(characterEncoding);
        // 设置字符集编码
        servletRequest.setCharacterEncoding("UTF-8");
        characterEncoding = servletRequest.getCharacterEncoding();
        System.out.println(characterEncoding);
        System.out.println("==========================");
        // 从请求中获取字符流
        BufferedReader reader = servletRequest.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("============================");
        // 获取servletRequest对象中存储的属性名称
        Enumeration<String> attributeNames = servletRequest.getAttributeNames();
        System.out.println("attribute:");
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = servletRequest.getAttribute(attributeName);
            System.out.println(attributeName + " => " + attribute);
        }
        System.out.println("======================");
        // 获取servletRequest对象中存储的参数名称
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        System.out.println("parameter:");
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameter = servletRequest.getParameter(parameterName);
            System.out.println(parameterName + " => " + parameter);
        }
        System.out.println("======================");
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        parameterMap.forEach((k, v) -> System.out.println(k + " => " + Arrays.toString(v)));

        System.out.println("======================");
        System.out.println("响应的字符集编码： " + servletResponse.getCharacterEncoding());
        servletResponse.setCharacterEncoding("UTF-8");
        System.out.println("响应的字符集编码： " + servletResponse.getCharacterEncoding());
        System.out.println("响应的字符集类型： " + servletResponse.getContentType());
        servletResponse.setContentType("text/html;charset=utf-8");
        System.out.println("响应的字符集类型： " + servletResponse.getContentType());
        // 向页面输出数据的输出流
        PrintWriter writer = servletResponse.getWriter();
        writer.println("login request processed");
        writer.flush();
        writer.close();
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    // Servlet销毁，不在提供服务
    // 在tomcat服务器关闭之前，Servlet被销毁
    @Override
    public void destroy() {
        System.out.println("Servlet destroy, no longer provide service");
    }
}
