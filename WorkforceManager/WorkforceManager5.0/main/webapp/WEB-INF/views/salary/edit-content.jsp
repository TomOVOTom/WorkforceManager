<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="salary-standard-form">
    <h2>编辑薪酬标准信息</h2>
    <form action="${pageContext.request.contextPath}/salary/update" method="post">
        <input type="hidden" name="id" value="${standard.id}">

        <div class="form-group">
            <label for="standardNumber">编号</label>
            <input type="text" id="standardNumber" name="standardNumber" value="${standard.standardNumber}" readonly>
        </div>

        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" id="name" name="name" value="${standard.name}" required>
        </div>

        <div class="form-group">
            <label for="name">登记人</label>
            <input type="text" id="creator" name="creator" value="${standard.creator}" required>
        </div>

        <div class="form-group">
            <label for="name">审核人</label>
            <input type="text" id="reviewer" name="reviewer" value="${standard.reviewer}" required>
        </div>

        <div class="form-group">
            <label for="total">总金额</label>
            <input type="double" id="total" name="total" value="${standard.totalAmount}">
        </div>

        <div class="form-group">
            <label for="status">状态</label>
            <input type="text" id="status" name="status" value="${standard.status}">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="${pageContext.request.contextPath}/employee/" class="btn btn-default">取消</a>
        </div>
    </form>
</div>