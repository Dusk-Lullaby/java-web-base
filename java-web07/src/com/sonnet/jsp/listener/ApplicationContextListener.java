package com.sonnet.jsp.listener;

import com.sonnet.jsp.jdbc.JdbcUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener // 表明这是一个监听器
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet上下文初始化");
        // 获取上下文
        ServletContext context = sce.getServletContext();
        String jdbcConfig = context.getInitParameter("jdbcConfig");
        InputStream is = this.getClass().getResourceAsStream(jdbcConfig);
        Properties properties = new Properties();
        try {
            properties.load(is);
            // 对数据源进行初始化操作
            JdbcUtil.initDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet上下文销毁");
        JdbcUtil.destroyDataSource();
    }
}
