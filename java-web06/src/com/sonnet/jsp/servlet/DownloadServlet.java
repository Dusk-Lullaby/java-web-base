package com.sonnet.jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private static final String DOWNLOAD_FILE = "D:\\idea_code\\develop\\code\\study\\javaweb-base\\javaweb-base\\java-web06\\upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取下载文件的名字
        String name = req.getParameter("name");
        File file = new File(DOWNLOAD_FILE, name);
        if (file.exists()) {
            // 获取下载的文件名的字节数据
            byte[] data = name.getBytes(StandardCharsets.UTF_8);
            // 转换编码格式，重新构建字符串，因为浏览器默认支持ISO_8859_1
            // 因此，要转换为这种编码下中文才能正常显示
            name = new String(data, StandardCharsets.ISO_8859_1);
            // 设置文件内容的处理方案，以附件的形式处理
            resp.setHeader("Content-Disposition", "attachment;filename=" + name);
            InputStream inputStream =  new FileInputStream(file);
            // 获取响应的输出流，这个流就会将信息输出到页面，从而形成下载的效果
            OutputStream outputStream = resp.getOutputStream();
            // 传输信息
            IOUtils.copy(inputStream,  outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("下载的文件不存在");
            writer.flush();
            writer.close();
        }
    }
}
