<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="department-form">
    <h2>添加部门</h2>
    <form action="${pageContext.request.contextPath}/department/add" method="post">
        <div class="form-group">
            <label for="name">部门名称</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="parentId">上级部门</label>
            <select id="parentId" name="parentId" class="form-control">
                <option value="0">无(一级部门)</option>
                <c:forEach items="${departments}" var="dept">
                    <option value="${dept.id}" data-level="${dept.level}">
                        ${dept.name}
                        <c:choose>
                            <c:when test="${dept.level == 1}">(一级部门)</c:when>
                            <c:when test="${dept.level == 2}">(二级部门)</c:when>
                        </c:choose>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="level">部门层级</label>
            <select id="level" name="level" required>
                <option value="1">一级部门</option>
                <option value="2">二级部门</option>
                <option value="3">三级部门</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">状态</label>
            <select id="status" name="status" required>
                <option value="1">正常</option>
                <option value="0">停用</option>
            </select>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="${pageContext.request.contextPath}/department/" class="btn btn-default">取消</a>
        </div>
    </form>
</div>

<style>
.department-form {
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.form-group input,
.form-group select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

.form-actions {
    margin-top: 20px;
    display: flex;
    gap: 10px;
}

.btn {
    padding: 8px 16px;
    border-radius: 4px;
    cursor: pointer;
    border: none;
    color: white;
    text-decoration: none;
}

.btn-primary {
    background-color: #1890ff;
}

.btn-default {
    background-color: #d9d9d9;
}

select:disabled {
    background-color: #f5f5f5;
}

.form-group input:focus,
.form-group select:focus {
    outline: none;
    border-color: #1890ff;
    box-shadow: 0 0 0 2px rgba(24,144,255,0.2);
}

.btn:hover {
    opacity: 0.8;
}
</style>

<script>
// 表单提交验证
document.querySelector('form').addEventListener('submit', function(e) {
    const level = parseInt(document.getElementById('level').value);
    const parentId = parseInt(document.getElementById('parentId').value);

    if (level > 1 && parentId === 0) {
        e.preventDefault();
        alert('非一级部门必须指定上级部门');
    }
});

// 根据选择的上级部门自动设置部门层级
document.getElementById('parentId').addEventListener('change', function() {
    const parentId = parseInt(this.value);
    const levelSelect = document.getElementById('level');

    if (parentId === 0) {
        levelSelect.value = '1';
    } else {
        const selectedOption = this.options[this.selectedIndex];
        const parentLevel = parseInt(selectedOption.getAttribute('data-level') || '1');
        levelSelect.value = Math.min(parentLevel + 1, 3).toString();
    }
});

// 根据选择的层级限制上级部门选项
document.getElementById('level').addEventListener('change', function() {
    const selectedLevel = parseInt(this.value);
    const parentIdSelect = document.getElementById('parentId');
    const options = parentIdSelect.options;

    if (selectedLevel === 1) {
        parentIdSelect.value = '0';
    }

    // 遍历所有选项，根据层级关系启用/禁用
    for (let i = 0; i < options.length; i++) {
        const option = options[i];
        const optionLevel = parseInt(option.getAttribute('data-level') || '0');

        if (selectedLevel === 1) {
            option.disabled = true;
        } else {
            option.disabled = optionLevel >= selectedLevel;
        }
    }
});
</script>