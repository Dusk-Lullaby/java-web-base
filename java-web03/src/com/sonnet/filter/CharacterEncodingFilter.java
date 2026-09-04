package com.sonnet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

/**
 * 字符集编码过滤器
 */
@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name="encoding", value="UTF-8")
})
public class CharacterEncodingFilter implements Filter {

    String encoding;

    public CharacterEncodingFilter() {
        System.out.println("Filter creation instance");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init");
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter filter process");
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        // 多个过滤器会形成一条过滤器链，当前过滤器做完事情之后必须调用chain.doFilter方法
        // 让下一个过滤器做事情，所有的过滤器做完事情之后，才会把请求送达Servlet，
        // 如果过滤的请求不需要Servlet来处理，那么就不需要调用chain.Servlet方法，直接使用response对象做出响应即可
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy");
    }
}
