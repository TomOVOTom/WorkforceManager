<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../common/layout.jsp">
    <jsp:param name="title" value="添加员工" />
    <jsp:param name="content" value="/WEB-INF/views/employee/add-content.jsp" />
</jsp:include>
