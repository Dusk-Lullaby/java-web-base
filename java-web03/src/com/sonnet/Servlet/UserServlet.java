package com.sonnet.Servlet;

import com.sonnet.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet 就表示标识的类是一个Servlet，不需要在web.xml中对该Servlet进行配置
@WebServlet("/showUserInfo")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 这个只是简单的数据模型，在这里演示使用，在实际开发的业务中，
        // 往往数据的获取都会由业务层处理业务时从DAO获取，然后组装成整个模型
        User user = new User("张三", "男", 25);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println(user.toString());
        writer.flush();
        writer.close();
    }
}
