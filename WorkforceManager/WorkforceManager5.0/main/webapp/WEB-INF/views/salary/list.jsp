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

<jsp:include page="../common/layout.jsp">
  <jsp:param name="title" value="薪酬标准列表" />
  <jsp:param name="content" value="/WEB-INF/views/salary/list-content.jsp" />
</jsp:include>