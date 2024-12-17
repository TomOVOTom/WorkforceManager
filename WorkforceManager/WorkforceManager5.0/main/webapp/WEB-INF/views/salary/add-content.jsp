<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/12/17
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增薪酬标准</title>
</head>
<body>
<h1>新增薪酬标准</h1>
<form action="${pageContext.request.contextPath}/salary/add" method="post">
    <label for="standardNumber">标准编号:</label>
    <input type="text" id="standardNumber" name="standardNumber" required><br><br>

    <label for="name">标准名称:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="totalAmount">总金额:</label>
    <input type="number" id="totalAmount" name="totalAmount" required><br><br>

    <label for="creator">创建人:</label>
    <input type="text" id="creator" name="creator" required><br><br>

    <label for="reviewer">审核人:</label>
    <input type="text" id="reviewer" name="reviewer" required><br><br>

    <label for="status">状态:</label>
    <select id="status" name="status" required>
        <option value="active">启用</option>
        <option value="inactive">停用</option>
    </select><br><br>

    <label for="createTime">创建时间:</label>
    <input type="date" id="createTime" name="createTime" required><br><br>

    <button type="submit">新增薪酬标准</button>
</form>
</body>
</html>

