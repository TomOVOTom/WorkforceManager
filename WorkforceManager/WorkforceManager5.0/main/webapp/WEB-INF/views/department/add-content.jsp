<div class="department-form">
    <form action="${pageContext.request.contextPath}/department/add" method="post">
        <div class="form-group">
            <label for="name">部门名称</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="parentId">上级部门</label>
            <select id="parentId" name="parentId">
                <option value="0">无(一级部门)</option>
                <c:forEach items="${parentDepartments}" var="dept">
                    <option value="${dept.id}">${dept.name}</option>
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
</script>