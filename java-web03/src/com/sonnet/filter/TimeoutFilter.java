package com.sonnet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 如果这里配置为/* 那么所有请求都将被拦截，登录也会被拦截，就会造成还没有登录就已经超时
// 所有需要在过滤的时候对某些不需要的请求进行放行
@WebFilter("/*")
public class TimeoutFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        requestURI = requestURI.replace(contextPath, "");
        if ("/".equals(requestURI) || "/showUserInfo".equals(requestURI) || requestURI.startsWith("/second")) {
            // 登录没有超时需要让下一个过滤器做事情
            chain.doFilter(request,response);
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) { // 登录超时直接让页面跳转至登录页面
                // 重定向
                response.sendRedirect("second.jsp");
            } else {
                // 登录没有超时需要让下一个过滤器做事情
                chain.doFilter(request,response);

            }
        }
    }
}
