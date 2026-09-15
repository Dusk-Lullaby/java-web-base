// 声明当前过滤器所在的包
package com.sonnet.jsp.filter;

// 导入用户业务层接口
import com.sonnet.jsp.service.UserService;
// 导入用户业务层实现类
import com.sonnet.jsp.service.impl.UserServiceImpl;
// 导入过滤器链对象
import jakarta.servlet.FilterChain;
// 导入 Servlet 异常类
import jakarta.servlet.ServletException;
// 导入过滤器注解
import jakarta.servlet.annotation.WebFilter;
// 导入支持 HTTP 请求的过滤器基类
import jakarta.servlet.http.HttpFilter;
// 导入 HTTP 请求对象
import jakarta.servlet.http.HttpServletRequest;
// 导入 HTTP 响应对象
import jakarta.servlet.http.HttpServletResponse;
// 导入 HTTP 会话对象
import jakarta.servlet.http.HttpSession;

// 导入输入输出异常类
import java.io.IOException;
// 导入响应输出流对象
import java.io.PrintWriter;

// 拦截当前 Web 应用中的所有请求
@WebFilter("/*")
// 定义权限过滤器并继承 HTTP 过滤器基类
public class PermissionFilter extends HttpFilter {

    // 创建用户业务对象，用于检查用户访问权限
    private UserService userService = new UserServiceImpl();

    // 重写过滤器的请求处理方法
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求的完整 URI
        String requestURI = request.getRequestURI();
        // 去掉项目上下文路径，只保留项目内部的请求路径
        String uri = requestURI.replace(request.getContextPath(), "");
        // 判断当前请求是否属于不需要登录验证的公开资源
        if ("/".equals(uri) || "/login".equals(uri) || uri.endsWith(".jsp") || uri.startsWith("/js")) {
            // 公开资源直接放行给后续过滤器或 Servlet 处理
            chain.doFilter(request, response);
        } else {
            // 获取当前请求对应的会话对象
            HttpSession session = request.getSession();
            // 从会话中获取登录时保存的用户名
            String username = (String) session.getAttribute("username");
            // 判断当前用户是否没有登录或登录会话已经失效
            if (username == null) {
                // 在控制台输出登录超时提示
                System.out.println("登录超时");
            } else {
                // 这里就应该去查询当前登录用户是否应用访问这个url的权限
                // 检查当前用户是否具有访问请求路径的权限
                if (userService.hasPermission(uri, username)) {
                    // 用户具有权限时放行当前请求
                    chain.doFilter(request, response);
                } else {
                    // 设置响应内容的字符编码，避免中文出现乱码
                    response.setCharacterEncoding("UTF-8");
                    // 获取向浏览器输出内容的字符流
                    PrintWriter writer = response.getWriter();
                    // 向浏览器输出无权访问提示
                    writer.print("没有访问权限");
                    // 刷新输出流，确保内容发送给浏览器
                    writer.flush();
                    // 关闭输出流并释放资源
                    writer.close();
                }
            }
        }
    }
}
