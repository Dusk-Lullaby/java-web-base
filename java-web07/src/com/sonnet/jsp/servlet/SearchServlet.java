package com.sonnet.jsp.servlet;

import com.alibaba.fastjson.JSONObject;
import com.sonnet.jsp.pojo.Student;
import com.sonnet.jsp.service.impl.StudentServiceImpl;
import com.sonnet.jsp.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        List<Student> students = studentService.searchStudents();
        Object json = JSONObject.toJSON(students);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();

    }
}
