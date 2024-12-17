<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="employee-form">
    <h2>编辑员工信息</h2>
    <form action="${pageContext.request.contextPath}/employee/update" method="post">
        <input type="hidden" name="id" value="${employee.id}">

        <div class="form-group">
            <label for="employeeNumber">工号</label>
            <input type="text" id="employeeNumber" name="employeeNumber" value="${employee.employeeNumber}" readonly>
        </div>

        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" id="name" name="name" value="${employee.name}" required>
        </div>

        <div class="form-group">
            <label for="gender">性别</label>
            <select id="gender" name="gender" required>
                <option value="男" ${employee.gender == '男' ? 'selected' : ''}>男</option>
                <option value="女" ${employee.gender == '女' ? 'selected' : ''}>女</option>
            </select>
        </div>

        <div class="form-group">
            <label for="hireDate">入职日期</label>
            <input type="date" id="hireDate" name="hireDate"
                   value="<fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd"/>" required>
        </div>

        <div class="form-group">
            <label for="status">状态</label>

            <div class="form-group">
                <label for="idCard">身份证号</label>
                <input type="text" id="idCard" name="idCard" value="${employee.idCard}">
            </div>

            <div class="form-group">
                <label for="phone">电话</label>
                <input type="tel" id="phone" name="phone" value="${employee.phone}">
            </div>

            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" id="email" name="email" value="${employee.email}">
            </div>

            <div class="form-group">
                <label for="department">部门</label>
                <input type="text" id="department" name="department" value="${employee.department}" required>
            </div>

            <div class="form-group">
                <label for="position">职位</label>
                <input type="text" id="position" name="position" value="${employee.position}" required>
            </div>

            <div class="form-group">
                <label for="status">状态</label>
                <select id="status" name="status" required>
                    <option value="在职" ${employee.status == '在职' ? 'selected' : ''}>在职</option>
                    <option value="离职" ${employee.status == '离职' ? 'selected' : ''}>离职</option>
                    <option value="休假" ${employee.status == '休假' ? 'selected' : ''}>休假</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">薪资</label>
                <input type="number" id="salary" name="salary" step="0.01" value="${employee.salary}" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存</button>
                <a href="${pageContext.request.contextPath}/employee/" class="btn btn-default">取消</a>
            </div>
    </form>
</div>