<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="department-list">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>部门名称</th>
            <th>上级部门</th>
            <th>层级</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${departments}" var="dept">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.name}</td>
                <td>
                    <c:forEach items="${departments}" var="parentDept">
                        <c:if test="${parentDept.id == dept.parentId}">
                            ${parentDept.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${dept.level}</td>
                <td>${dept.status == 1 ? '正常' : '停用'}</td>
                <td>${dept.createTime}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/department/edit/${dept.id}">编辑</a>
                    <a href="javascript:void(0)" onclick="confirmDelete(${dept.id})">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmDelete(id) {
        if (confirm('确定要删除这个部门吗？')) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '${pageContext.request.contextPath}/department/delete';

            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'id';
            input.value = id;

            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>