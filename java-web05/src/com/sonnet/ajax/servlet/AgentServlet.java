package com.sonnet.ajax.servlet;

import com.alibaba.fastjson.JSONObject;
import com.sonnet.ajax.pojo.Agent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/searchAgents")
public class AgentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("region");
        List<Agent> agents = new ArrayList<>();
        Random random = new Random();
        // 随机返回20条以内的数据
        int count = random.nextInt(20);
        for (int i = 0; i < count; i++) {
            Agent agent = new Agent();
            agent.setAid(1 + i);
            agent.setAno("ano" + (1 + i));
            agent.setAname("代理商" + (1 + i));
            agent.setAregion(region);
            agents.add(agent);
        }
        //req.getSession().setAttribute("agents", agents);
        //// 返回一个页面，这个页面就会被前端的load函数接收到
        //resp.sendRedirect("data.jsp");

        resp.setCharacterEncoding("UTF-8");
        // 设置响应头信息:返回的数据类型是JSON格式的数据类型
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(JSONObject.toJSONString(agents));
        writer.flush();
        writer.close();
    }
}
