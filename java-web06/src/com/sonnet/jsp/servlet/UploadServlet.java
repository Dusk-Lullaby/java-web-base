package com.sonnet.jsp.servlet;

import com.sonnet.excel.ExcelUtil;
import com.sonnet.pojo.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private static final String SAVE_DIR = "D:\\idea_code\\develop\\code\\study\\javaweb-base\\javaweb-base\\java-web06\\upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断当前文件是否为文件上传请求
        // 文件上传表单必须使用enctype="multipart/form-data"
        if (JakartaServletFileUpload.isMultipartContent(req)) {
            // 创建磁盘文件项工厂的构建器
            DiskFileItemFactory.Builder builder = DiskFileItemFactory.builder();
            // 设置普通表单字段的默认字符集编码为UTF-8
            // 用于减少中文表单参数出现乱码的情况
            builder.setCharset("UTF-8");
            // 获取Java运行环境提供的系统临时目录
            String tempDirectory = System.getProperty("java.io.tmpdir");
            // 将临时目录的字符串转换为Path对象
            Path repository = Path.of(tempDirectory);
            // 设置上传文件的临时存储目录
            // 当上传内容超过内存缓存区域阈值时，会临时存储到个目录
            builder.setPath(repository);
            // 设置内存缓冲区阈值为4096字节，也就是4MB
            // 文件项不超过4MB时主要保存在内存中
            // 文件项超过4MB时会使用磁盘临时文件
            builder.setThreshold(4096 * 1024);
            // 根据上面设置的字符编码、临时目录和阈值创建文件项工厂
            DiskFileItemFactory factory = builder.get();
            // 使用磁盘文件项工厂创建Jakarta Servlet 6文件上传解析器
            // Tomcat 11需要使用JakartaServletDiskFileUpload
            JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);
            // 设置HTTP上传请求头使用UTF-8编码
            upload.setHeaderCharset(StandardCharsets.UTF_8);
            // 设置每一个上传文件的最大大小为05M
            upload.setMaxFileSize(50 * 1024 * 1024);
            // 设置每次上传的所有文件的总大小为50M
            upload.setMaxSize(50 * 1024 * 1024);
            try {
                // 解析文件上传请求，取得所有表单项
                List<DiskFileItem> fileItems = upload.parseRequest(req);
                // 遍历所有表单项
                for (DiskFileItem fileItem : fileItems) {
                    // 判断当前项目是否为普通表单字段
                    // 例如用户名、描述等普通<input>元素
                    if (fileItem.isFormField()) {
                        // 获取普通表单字段的name属性
                        String fieldName = fileItem.getFieldName();
                        // 获取普通表单字段的值
                        String fieldValue = fileItem.getString(StandardCharsets.UTF_8);
                        System.out.println(fieldName + " => " + fieldValue);
                    } else { // 当前项目是上传的文件
                        File dir = new File(SAVE_DIR);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        // 创建保存的文件
                        File saveFile = new File(dir, fileItem.getName());
                        // 获取上传文件的输入流
                        InputStream inputStream = fileItem.getInputStream();
                        List<Student> studentList = ExcelUtil.readExcel(inputStream,  Student.class);
                        // 获取上传文件的输出流
                        OutputStream outputStream = new FileOutputStream(saveFile);
                        // 将输入流中的信息拷贝至输出流中，这就是文件保存
                        IOUtils.copy(inputStream, outputStream);
                        // 关闭流
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly(outputStream);
                    }
                }
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().print("上传成功");
            } catch (FileUploadException exception) {
                throw new ServletException("解析文件上传请求失败", exception);
            }
        } else { // 抛出运行时异常
            throw new RuntimeException("请求头中未发现multipart/form-data");
        }
    }
}
