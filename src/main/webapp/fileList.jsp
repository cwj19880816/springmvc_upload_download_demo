<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/3 0003
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="f" items="${requestScope.map}">
    <c:url value="/download" var="downurl">
        <c:param name="fileName" value="${f.key}"></c:param>
    </c:url>
    文件名：${f.value}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${downurl}">下载</a><br/>
</c:forEach>
</body>
</html>
