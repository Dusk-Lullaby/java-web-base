package com.sonnet.jsp.servlet;

import com.sonnet.jsp.pojo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/showData")
public class ManagerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 使用session存储
        req.getSession().setAttribute("user", new User("李四", "女"));

        // 使用request存储
        req.setAttribute("user", new User("张三", "男"));

        List<User> userList = Arrays.asList(new User("王五", "其他"), new User("老六", "男"));
        req.getSession().setAttribute("users", userList);

        Map<String, Object> map = new HashMap<>();
        map.put("admin", 80);
        map.put("test", 90);
        req.getSession().setAttribute("data", map);

        // 转发，地址栏不变，依旧是showData
        req.getRequestDispatcher("manage.jsp").forward(req, resp);

        // 重定向，地址栏会改变，变为manage.jsp
        //resp.sendRedirect("manage.jsp");
    }
}
