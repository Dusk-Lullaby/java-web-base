package com.sonnet.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        if ("admin".equals(username)) {
            // 这里不可以使用println，使用println传输的数据是"1/r/n"
            writer.print(1);  // 1表示用户名已经存在
        } else {
            writer.print(0);  // 用户名可以注册
        }
        writer.flush();
        writer.close();
    }
}
