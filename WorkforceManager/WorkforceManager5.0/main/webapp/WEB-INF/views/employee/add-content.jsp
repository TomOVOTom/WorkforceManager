<div class="employee-form">
    <h2>添加员工</h2>
    <div class="employee-form">
        <h2>添加员工</h2>
        <form action="${pageContext.request.contextPath}/employee/add" method="post">
            <div class="form-group">
                <label for="employeeNumber">工号</label>
                <input type="text" id="employeeNumber" name="employeeNumber" value="EMP001" required>
            </div>

            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" id="name" name="name" value="张三" required>
            </div>

            <div class="form-group">
                <label for="gender">性别</label>
                <select id="gender" name="gender" required>//必选项
                    <option value="男" selected>男</option>
                    <option value="女">女</option>
                </select>
            </div>

            <div class="form-group">
                <label for="birthDate">出生日期</label>
                <input type="date" id="birthDate" name="birthDate" value="1990-01-01" required>
                //日期输入框
            </div>

            <div class="form-group">
                <label for="idCard">身份证号</label>
                <input type="text" id="idCard" name="idCard" value="110101199001011234" required>
            </div>

            <div class="form-group">
                <label for="phone">电话</label>
                <input type="tel" id="phone" name="phone" value="13800138000" required>
            </div>

            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" id="email" name="email" value="zhangsan@example.com">
            </div>

            <div class="form-group">
                <label for="address">地址</label>
                <input type="text" id="address" name="address" value="北京市朝阳区">
            </div>

            <div class="form-group">
                <label for="department">部门</label>
                <input type="text" id="department" name="department" value="技术部" required>
            </div>

            <div class="form-group">
                <label for="position">职位</label>
                <input type="text" id="position" name="position" value="工程师" required>
            </div>

            <div class="form-group">
                <label for="hireDate">入职日期</label>
                <input type="date" id="hireDate" name="hireDate" value="2023-01-01" required>
            </div>

            <div class="form-group">
                <label for="status">状态</label>
                <select id="status" name="status" required>
                    <option value="在职" selected>在职</option>
                    <option value="离职">离职</option>
                    <option value="休假">休假</option>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">薪资</label>
                <input type="number" id="salary" name="salary" step="0.01" value="10000.00" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存</button>
                <a href="${pageContext.request.contextPath}/employee/" class="btn btn-default">取消</a>
            </div>
        </form>

    </div>
</div>