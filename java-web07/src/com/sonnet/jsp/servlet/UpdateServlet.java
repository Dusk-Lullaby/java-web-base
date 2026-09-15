package com.sonnet.jsp.servlet;

import com.sonnet.jsp.service.StudentService;
import com.sonnet.jsp.service.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        int result = studentService.updateStudent(id, name, sex, age);
        PrintWriter writer = resp.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }
}
