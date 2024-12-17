<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/12/17
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="salaryStandard-list">
  <div class="page-header">
    <h2>薪酬标准列表</h2>
    <a href="${pageContext.request.contextPath}/salary/add" class="btn btn-primary">添加薪酬标准</a>
  </div>

  <table class="table">
    <thead>
    <tr>
      <th>编号</th>
      <th>名称</th>
      <th>金额</th>
      <th>登记人</th>
      <th>审核人</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${salarystandards}" var="standard">
      <tr>
        <td>${standard.standardNumber}</td>
        <td>${standard.name}</td>
        <td>${standard.totalAmount}</td>
        <td>${standard.creator}</td>
        <td>${standard.reviewer}</td>
        <td>${standard.status}</td>
        <td>
          <a href="${pageContext.request.contextPath}/salary/view/${standard.id}" class="btn btn-info">查看</a>
          <a href="${pageContext.request.contextPath}/salary/edit/${standard.id}" class="btn btn-warning">编辑</a>
          <a href="${pageContext.request.contextPath}/salary/enable/${standard.id}" class="btn btn-primary">启用</a>
          <a href="${pageContext.request.contextPath}/salary/disable/${standard.id}" class="btn btn-primary">弃用</a>
          <button onclick="deleteEmployee(${standard.id})" class="btn btn-danger">删除</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script>

</script>
