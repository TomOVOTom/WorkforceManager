<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="employee-detail">
    <h2>员工详情</h2>

    <div class="detail-section">
        <h3>基本信息</h3>
        <table class="detail-table">
            <tr>
                <th>工号：</th>
                <td>${employee.employeeNumber}</td>
                <th>姓名：</th>
                <td>${employee.name}</td>
            </tr>
            <tr>
                <th>性别：</th>
                <td>${employee.gender}</td>
                <th>出生日期：</th>
                <td><fmt:formatDate value="${employee.birthDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <th>身份证号：</th>
                <td>${employee.idCard}</td>
                <th>电话：</th>
                <td>${employee.phone}</td>
            </tr>
            <tr>
                <th>邮箱：</th>
                <td>${employee.email}</td>
                <th>地址：</th>
                <td>${employee.address}</td>
            </tr>
        </table>
    </div>

    <div class="detail-section">
        <h3>工作信息</h3>
        <table class="detail-table">
            <tr>
                <th>部门：</th>
                <td>${employee.department}</td>
                <th>职位：</th>
                <td>${employee.position}</td>
            </tr>
            <tr>
                <th>入职日期：</th>
                <td><fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd"/></td>
                <th>状态：</th>
                <td>${employee.status}</td>
            </tr>
            <tr>
                <th>薪资：</th>
                <td colspan="3">￥<fmt:formatNumber value="${employee.salary}" pattern="#,##0.00"/></td>
            </tr>
        </table>
    </div>

    <div class="detail-actions">
        <a href="${pageContext.request.contextPath}/employee/edit/${employee.id}" class="btn btn-primary">编辑</a>
        <a href="${pageContext.request.contextPath}/employee/" class="btn btn-default">返回列表</a>
    </div>
</div>