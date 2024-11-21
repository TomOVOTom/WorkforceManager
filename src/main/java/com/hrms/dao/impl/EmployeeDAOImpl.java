package com.hrms.dao.impl;

import com.hrms.dao.EmployeeDAO;
import com.hrms.model.Employee;
import com.hrms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void insert(Employee employee) throws Exception {
        String sql = "INSERT INTO employees (employee_number, name, gender, birth_date, " +
                "id_card, phone, email, address, department, position, hire_date, status, salary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getEmployeeNumber());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getGender());
            pstmt.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
            pstmt.setString(5, employee.getIdCard());
            pstmt.setString(6, employee.getPhone());
            pstmt.setString(7, employee.getEmail());
            pstmt.setString(8, employee.getAddress());
            pstmt.setString(9, employee.getDepartment());
            pstmt.setString(10, employee.getPosition());
            pstmt.setDate(11, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setString(12, employee.getStatus());
            pstmt.setDouble(13, employee.getSalary());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Employee employee) throws Exception {
        String sql = "UPDATE employees SET name=?, gender=?, birth_date=?, id_card=?, " +
                "phone=?, email=?, address=?, department=?, position=?, hire_date=?, " +
                "status=?, salary=? WHERE employee_number=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getGender());
            pstmt.setDate(3, new java.sql.Date(employee.getBirthDate().getTime()));
            pstmt.setString(4, employee.getIdCard());
            pstmt.setString(5, employee.getPhone());
            pstmt.setString(6, employee.getEmail());
            pstmt.setString(7, employee.getAddress());
            pstmt.setString(8, employee.getDepartment());
            pstmt.setString(9, employee.getPosition());
            pstmt.setDate(10, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setString(11, employee.getStatus());
            pstmt.setDouble(12, employee.getSalary());
            pstmt.setString(13, employee.getEmployeeNumber());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Employee findById(int id) throws Exception {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        }
        return employees;
    }

    @Override
    public List<Employee> findByDepartment(String department) throws Exception {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        }
        return employees;
    }

    @Override
    public Employee findByEmployeeNumber(String employeeNumber) throws Exception {
        String sql = "SELECT * FROM employees WHERE employee_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employeeNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        }
        return null;
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setEmployeeNumber(rs.getString("employee_number"));
        employee.setName(rs.getString("name"));
        employee.setGender(rs.getString("gender"));
        employee.setBirthDate(rs.getDate("birth_date"));
        employee.setIdCard(rs.getString("id_card"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setAddress(rs.getString("address"));
        employee.setDepartment(rs.getString("department"));
        employee.setPosition(rs.getString("position"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setStatus(rs.getString("status"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}