<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="department-list">
  <div class="actions">
    <a href="${pageContext.request.contextPath}/department/add" class="btn btn-primary">添加部门</a>
  </div>

  <table class="table">
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
        <td>${dept.parentId == 0 ? '无' : dept.parentId}</td>
        <td>
          <c:choose>
            <c:when test="${dept.level == 1}">一级部门</c:when>
            <c:when test="${dept.level == 2}">二级部门</c:when>
            <c:when test="${dept.level == 3}">三级部门</c:when>
          </c:choose>
        </td>
        <td>
            <span class="status-badge ${dept.status == 1 ? 'status-normal' : 'status-disabled'}">
                ${dept.status == 1 ? '正常' : '停用'}
            </span>
        </td>
        <td>${dept.createdAt}</td>
        <td>
          <div class="btn-group">
            <a href="${pageContext.request.contextPath}/department/edit/${dept.id}" class="btn btn-warning">编辑</a>
            <button onclick="confirmDelete(${dept.id})" class="btn btn-danger">删除</button>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<style>
  .department-list {
    padding: 20px;
  }

  .table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  }

  .table th, .table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: left;
  }

  .table th {
    background-color: #f5f5f5;
    font-weight: bold;
  }

  .table tr:hover {
    background-color: #f9f9f9;
  }

  .status-badge {
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
    display: inline-block;
  }

  .status-normal {
    background-color: #e6f7e6;
    color: #52c41a;
  }

  .status-disabled {
    background-color: #fff1f0;
    color: #ff4d4f;
  }

  .btn-group {
    display: flex;
    gap: 8px;
  }

  .actions {
    margin-bottom: 20px;
  }

  .btn {
    padding: 6px 12px;
    border-radius: 4px;
    cursor: pointer;
    text-decoration: none;
    border: none;
    color: white;
    transition: opacity 0.2s;
  }

  .btn:hover {
    opacity: 0.8;
  }

  .btn-primary {
    background-color: #1890ff;
  }

  .btn-warning {
    background-color: #faad14;
  }

  .btn-danger {
    background-color: #ff4d4f;
  }
</style>

<script>
  function confirmDelete(id) {
    if (!id) {
      alert('部门ID不能为空');
      return;
    }

    if (confirm('确定要删除这个部门吗？')) {
      fetch('${pageContext.request.contextPath}/department/delete', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'id=' + id
      }).then(response => {
        if (!response.ok) {
          return response.text().then(error => {
            throw new Error(error);
          });
        }
        window.location.reload();
      }).catch(error => {
        alert('删除失败：' + error.message);
      });
    }
  }
</script>