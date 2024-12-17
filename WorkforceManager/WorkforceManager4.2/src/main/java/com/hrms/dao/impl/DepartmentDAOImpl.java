package com.hrms.dao.impl;

import com.hrms.dao.DepartmentDAO;
import com.hrms.model.Department;
import com.hrms.util.DBUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.hrms.util.DBUtil.getConnection;

public class DepartmentDAOImpl implements DepartmentDAO {

   private Department extractDepartmentFromResultSet(ResultSet rs) throws SQLException {
    Department dept = new Department();
    dept.setId(rs.getInt("id"));
    dept.setName(rs.getString("name"));
    dept.setParentId(rs.getInt("parent_id"));
    dept.setLevel(rs.getInt("level"));
    dept.setStatus(rs.getInt("status"));
    // 处理 timestamp 类型
    Timestamp timestamp = rs.getTimestamp("created_at");
    if (timestamp != null) {
        dept.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp));
    }
    return dept;
}

  @Override
public List<Department> findAll() throws Exception {
    List<Department> departments = new ArrayList<>();
    String sql = "SELECT * FROM departments ORDER BY level, id";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Department dept = new Department();
            dept.setId(rs.getInt("id"));
            dept.setName(rs.getString("name"));
            dept.setParentId(rs.getInt("parent_id"));
            dept.setLevel(rs.getInt("level"));
            dept.setStatus(rs.getInt("status"));
            dept.setCreatedAt(rs.getString("created_at"));
            departments.add(dept);

            // 添加调试信息
            System.out.println("从数据库读取部门: ID=" + dept.getId() +
                             ", Name=" + dept.getName() +
                             ", Level=" + dept.getLevel());
        }
    }
    return departments;
}

    @Override
    public Department findById(int id) throws Exception {
        String sql = "SELECT * FROM departments WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDepartmentFromResultSet(rs);
            }
            rs.close();
        }
        return null;
    }

   @Override
public int insert(Department department) throws Exception {
    String sql = "INSERT INTO departments (name, parent_id, level, status, created_at) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, department.getName());
        stmt.setInt(2, department.getParentId());
        stmt.setInt(3, department.getLevel());
        stmt.setInt(4, department.getStatus());
        stmt.setString(5, department.getCreatedAt());

        stmt.executeUpdate();

        // 获取生成的ID
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    }
    return 0;
}

    @Override
    public void update(Department department) throws Exception {
        String sql = "UPDATE departments SET name = ?, parent_id = ?, level = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getParentId());
            stmt.setInt(3, department.getLevel());
            stmt.setInt(4, department.getStatus());
            stmt.setInt(5, department.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM departments WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Department> findByLevel(int level) throws Exception {
        String sql = "SELECT * FROM departments WHERE level = ?";
        List<Department> departments = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, level);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    departments.add(extractDepartmentFromResultSet(rs));
                }
            }
        }
        return departments;
    }

    @Override
    public List<Department> findByParentId(int parentId) throws Exception {
        String sql = "SELECT * FROM departments WHERE parent_id = ?";
        List<Department> departments = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, parentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    departments.add(extractDepartmentFromResultSet(rs));
                }
            }
        }
        return departments;
    }

    @Override
    public int countEmployeesByDepartment(int departmentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM employees WHERE department_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}