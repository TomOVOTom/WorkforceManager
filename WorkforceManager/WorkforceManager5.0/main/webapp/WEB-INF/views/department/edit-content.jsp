<div class="department-form">
    <form action="${pageContext.request.contextPath}/department/update" method="post">
        <input type="hidden" name="id" value="${department.id}">

        <div class="form-group">
            <label for="name">部门名称</label>
            <input type="text" id="name" name="name" value="${department.name}" required>
        </div>

        <div class="form-group">
            <label for="parentId">上级部门</label>
            <select id="parentId" name="parentId">
                <option value="0">无(一级部门)</option>
                <c:forEach items="${parentDepartments}" var="dept">
                    <option value="${dept.id}" ${dept.id == department.parentId ? 'selected' : ''}>
                            ${dept.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="level">部门层级</label>
            <select id="level" name="level" required>
                <option value="1" ${department.level == 1 ? 'selected' : ''}>一级部门</option>
                <option value="2" ${department.level == 2 ? 'selected' : ''}>二级部门</option>
                <option value="3" ${department.level == 3 ? 'selected' : ''}>三级部门</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">状态</label>
            <select id="status" name="status" required>
                <option value="1" ${department.status == 1 ? 'selected' : ''}>正常</option>
                <option value="0" ${department.status == 0 ? 'selected' : ''}>停用</option>
            </select>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="${pageContext.request.contextPath}/department/" class="btn btn-default">取消</a>
        </div>
    </form>
</div>

<script>
    document.getElementById('level').addEventListener('change', function() {
        const level = parseInt(this.value);
        const parentIdSelect = document.getElementById('parentId');

        if (level === 1) {
            parentIdSelect.value = "0";
            parentIdSelect.disabled = true;
        } else {
            parentIdSelect.disabled = false;
        }
    });

    // 页面加载时初始化父部门选择状态
    window.addEventListener('load', function() {
        const level = parseInt(document.getElementById('level').value);
        const parentIdSelect = document.getElementById('parentId');

        if (level === 1) {
            parentIdSelect.value = "0";
            parentIdSelect.disabled = true;
        }
    });
</script>