# JavaWeb Base

一个用于学习 Java Web 基础知识的示例仓库，主要记录 JSP、Servlet、`web.xml` 配置以及 Tomcat 部署相关内容。

当前项目使用传统 Java Web 工程结构，适合初学者理解浏览器请求、Servlet 映射、JSP 动态页面和 Web 应用部署流程。

## 技术栈

- Java 17
- Jakarta Servlet API
- JSP
- Apache Tomcat 11
- IntelliJ IDEA

> Tomcat 10 及以上版本使用 `jakarta.servlet.*` 命名空间。本项目中的 Servlet 代码不能直接运行在仅支持 `javax.servlet.*` 的旧版 Tomcat 中。

## 运行环境

建议准备以下环境：

1. JDK 17 或更高版本
2. IntelliJ IDEA Ultimate
3. Apache Tomcat 11
4. Git

本项目暂未使用 Maven 或 Gradle，Servlet API 由 IntelliJ IDEA 中配置的 Tomcat 服务器提供。

## 本地运行

### 1. 克隆项目

```bash
git clone git@github.com:Dusk-Lullaby/java-web-base.git
cd java-web-base
```

也可以使用 GitHub 提供的 HTTPS 地址克隆项目。

### 2. 使用 IntelliJ IDEA 打开

使用 IntelliJ IDEA 打开项目根目录，并为 `jsp01` 配置 Java Web 模块：

1. 打开 **File → Project Structure → Project**，将项目 SDK 设置为 JDK 17。
2. 打开 **Modules → jsp01**，将 `jsp01/src` 标记为 Sources。
3. 为 `jsp01` 添加 Web Facet。
4. 将 Web Resource Directory 设置为 `jsp01/webapp`。
5. 将 Deployment Descriptor 设置为 `jsp01/webapp/WEB-INF/web.xml`。

### 3. 配置 Tomcat

1. 打开 **Run → Edit Configurations**。
2. 新建 **Tomcat Server → Local**。
3. 将 Application Server 设置为本机 Tomcat 11。
4. 在 **Deployment** 中添加 `jsp01:Web exploded`。
5. 将 Application context 设置为 `/jsp01`。
6. 启动 Tomcat。

### 4. 访问页面

假设 Tomcat 使用默认的 `8080` 端口：

```text
JSP 首页：http://localhost:8080/jsp01/
```

如果修改了 Tomcat 端口或 Application context，请使用实际配置的地址。

如果写成 `<url-pattern>hello</url-pattern>`，Tomcat 会认为映射格式无效，导致 Web 应用部署失败。

## 注意事项

- `.idea/`、`*.iml` 和 `out/` 等本机配置及构建产物不会提交到 GitHub。
- 克隆项目后，需要根据本机环境重新配置 JDK、Tomcat、Web Facet 和部署 Artifact。
- JSP 文件应使用 UTF-8 编码，避免中文内容乱码。
- `WEB-INF` 目录不能通过浏览器直接访问，但其中的 `web.xml` 会由 Servlet 容器读取。

## License

本项目用于个人学习与交流。
