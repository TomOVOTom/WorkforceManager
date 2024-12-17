<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="error-page">
    <div class="error-content">
        <h2>出错了！</h2>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/employee/" class="btn btn-primary">返回首页</a>
        </div>
    </div>
</div>