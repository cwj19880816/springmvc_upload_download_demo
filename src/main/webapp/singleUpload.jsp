<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/3 0003
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="singleUpload" method="post" enctype="multipart/form-data">
    文件：<input type="file" name="file"/><br/>
    <input type="submit" value="上传"/><span style="color: green">${requestScope.sucmsg}</span>
</form>
<a href="listFile">显示已上传文件</a>
</body>
</html>
