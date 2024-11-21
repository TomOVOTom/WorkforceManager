<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="employee-list">
    <div class="page-header">
        <h2>员工列表</h2>
        <a href="${pageContext.request.contextPath}/employee/add" class="btn btn-primary">添加员工</a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>工号</th>
            <th>姓名</th>
            <th>部门</th>
            <th>职位</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="emp">
            <tr>
                <td>${emp.employeeNumber}</td>
                <td>${emp.name}</td>
                <td>${emp.department}</td>
                <td>${emp.position}</td>
                <td>${emp.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employee/view/${emp.id}" class="btn btn-info">查看</a>
                    <a href="${pageContext.request.contextPath}/employee/edit/${emp.id}" class="btn btn-warning">编辑</a>
                    <button onclick="deleteEmployee(${emp.id})" class="btn btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function deleteEmployee(id) {
        if (confirm('确定要删除这名员工吗？')) {
            fetch('${pageContext.request.contextPath}/employee/delete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'id=' + id
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                }
            });
        }
    }
</script>