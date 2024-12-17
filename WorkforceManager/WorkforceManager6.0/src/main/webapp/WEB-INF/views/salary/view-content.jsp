<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>薪酬标准详情</h2>
<div class="salary-standard-detail">
    <!-- 添加调试信息 -->
    <c:if test="${empty standard}">
        <div class="error-message">
            <p>未找到薪酬标准数据</p>
            <p>Debug信息：</p>
            <p>请求路径: ${pageContext.request.requestURI}</p>
            <p>查询参数: ${pageContext.request.queryString}</p>
        </div>
    </c:if>

    <c:if test="${not empty standard}">
        <div class="detail-section">
            <h3>基本信息</h3>
            <table class="detail-table">
                <tr>
                    <th>编号：</th>
                    <td>${standard.standardNumber}</td>
                </tr>
                <tr>
                    <th>名称：</th>
                    <td>${standard.name}</td>
                </tr>
                <tr>
                    <th>总金额：</th>
                    <td>${standard.totalAmount}</td>
                </tr>
                <tr>
                    <th>创建人：</th>
                    <td>${standard.creator}</td>
                </tr>
                <tr>
                    <th>审核人：</th>
                    <td>${standard.reviewer}</td>
                </tr>
                <tr>
                    <th>状态：</th>
                    <td>${standard.status}</td>
                </tr>
            </table>
        </div>
    </c:if>
</div>

    <div class="detail-section">
        <h3>薪酬项目信息</h3>
        <table class="detail-table">

        </table>
    </div>

    <div class="detail-actions">
        <a href="${pageContext.request.contextPath}/salary/edit/${standard.id}" class="btn btn-primary">编辑</a>
        <a href="${pageContext.request.contextPath}/salary/" class="btn btn-default">返回列表</a>
    </div>
</div>