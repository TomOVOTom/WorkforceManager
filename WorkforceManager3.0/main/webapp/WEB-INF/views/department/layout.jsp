<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>部门管理 - ${param.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>人力资源管理系统</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/department/">部门列表</a></li>
                <li><a href="${pageContext.request.contextPath}/department/add">添加部门</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>${param.title}</h2>
        <jsp:include page="${param.content}" />
    </main>

    <footer>
        <p>&copy; 2024 人力资源管理系统</p>
    </footer>
</div>
</body>
</html>