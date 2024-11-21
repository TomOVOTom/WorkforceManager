package com.hrms.dao.impl;

import com.hrms.dao.DepartmentDAO;
import com.hrms.model.Department;
import com.hrms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public void insert(Department department) throws Exception {
        String sql = "INSERT INTO departments (name, parent_id, level, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getParentId());
            pstmt.setInt(3, department.getLevel());
            pstmt.setInt(4, department.getStatus());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Department department) throws Exception {
        String sql = "UPDATE departments SET name = ?, parent_id = ?, level = ?, status = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getParentId());
            pstmt.setInt(3, department.getLevel());
            pstmt.setInt(4, department.getStatus());
            pstmt.setInt(5, department.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM departments WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Department findById(int id) throws Exception {
        String sql = "SELECT * FROM departments WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractDepartmentFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Department> findAll() throws Exception {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                departments.add(extractDepartmentFromResultSet(rs));
            }
        }
        return departments;
    }

    @Override
    public List<Department> findByLevel(int level) throws Exception {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE level = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, level);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                departments.add(extractDepartmentFromResultSet(rs));
            }
        }
        return departments;
    }

    @Override
    public List<Department> findByParentId(int parentId) throws Exception {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE parent_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, parentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                departments.add(extractDepartmentFromResultSet(rs));
            }
        }
        return departments;
    }

    @Override
    public int countEmployeesByDepartment(int departmentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM employees e " +
                "JOIN departments d ON e.department = d.name " +
                "WHERE d.id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Department extractDepartmentFromResultSet(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        department.setParentId(rs.getInt("parent_id"));
        department.setLevel(rs.getInt("level"));
        department.setStatus(rs.getInt("status"));
        // 只有当create_time列存在时才设置
        try {
            String createTime = rs.getString("create_time");
            if (createTime != null) {
                department.setCreateTime(createTime);
            }
        } catch (SQLException e) {
            // 如果列不存在，忽略这个错误
        }

        return department;
    }
}