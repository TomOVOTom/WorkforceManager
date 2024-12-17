<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>人力资源管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/department.css">
<body>
<div class="container">
    <header>
        <h1>人力资源管理系统</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/employee/">员工管理</a></li>
                <li><a href="${pageContext.request.contextPath}/department/">部门管理</a></li>
                <li><a href="${pageContext.request.contextPath}/salary/">薪资管理</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <jsp:include page="${param.content}" />
    </main>

    <footer>
        <p>&copy; 2024 人力资源管理系统</p>
    </footer>
</div>
</body>
</html>