<%--
  Created by IntelliJ IDEA.
  User: sonnet
  Date: 2026/9/7
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传和下载</title>
</head>
<body>
<%--使用form表单进行文件上传的时候，必须要设置enctype属性，--%>
<%--并且这个属性值必须是multipart/form-data--%>
<form action="upload" enctype="multipart/form-data" method="post">
    <input type="text" name="name">
    <input type="file" name="uploadFile">
    <input type="submit" value="上传">
</form>

<input type="file" id="uploadFile">
<input type="button" value="上传" id="uploadBtn">
<%--超链接默认发送请求的方式是get--%>
<a href="download?name=图片.png">图片.png</a>
</body>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
    $(function () {
        $("#uploadBtn").click(function () {
            // 创建一个表单数据，主要用来模拟表单数据
            let formData = new FormData();
            formData.append("file", $("#uploadFile")[0].files[0]);
            formData.append("admin", "admin");
            formData.append("sex", "sex");
            $.ajax({
                url:"upload",
                type:"post",
                data: formData,
                // 告诉jQuery不要处理数据
                processData: false,
                // 告诉jQuery不要设置内容的类型
                contentType: false,
                success: function (resp) {
                    alert(resp);
                },
                error: function (xhr) {

                }
            })
        })
    })
</script>
</html>
