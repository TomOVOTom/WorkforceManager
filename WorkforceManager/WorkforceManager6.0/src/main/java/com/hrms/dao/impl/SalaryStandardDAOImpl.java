package com.hrms.dao.impl;

import com.hrms.dao.SalaryStandardDAO;
import com.hrms.model.SalaryStandard;
import com.hrms.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryStandardDAOImpl implements SalaryStandardDAO {
    @Override
    public boolean add(SalaryStandard salaryStandard) {
        String sql = "INSERT INTO salary_standards (standard_number, name, total_amount, creator, reviewer, status, created_at, isApproval) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryStandard.getStandardNumber());
            pstmt.setString(2, salaryStandard.getName());
            pstmt.setDouble(3, salaryStandard.getTotalAmount());
            pstmt.setString(4, salaryStandard.getCreator());
            pstmt.setString(5, salaryStandard.getReviewer());
            pstmt.setString(6, salaryStandard.getStatus());
            pstmt.setString(7, salaryStandard.getCreateTime());
            pstmt.setBoolean(8, salaryStandard.isIs_approval());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(SalaryStandard salaryStandard) {
        String sql = "UPDATE salary_standards SET standard_number = ?, name = ?, total_amount = ?, creator = ?, reviewer = ?, status = ?, created_at = ?, isApproval = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryStandard.getStandardNumber());
            pstmt.setString(2, salaryStandard.getName());
            pstmt.setDouble(3, salaryStandard.getTotalAmount());
            pstmt.setString(4, salaryStandard.getCreator());
            pstmt.setString(5, salaryStandard.getReviewer());
            pstmt.setString(6, salaryStandard.getStatus());
            pstmt.setString(7, salaryStandard.getCreateTime());
            pstmt.setBoolean(8, salaryStandard.isIs_approval());
            pstmt.setInt(8, salaryStandard.getId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM salary_standards WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SalaryStandard> getAllSalaryStandards() {
        List<SalaryStandard> salaryStandards = new ArrayList<>();
        String sql = "SELECT * FROM salary_standards";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SalaryStandard salaryStandard = new SalaryStandard(
                        rs.getInt("id"),
                        rs.getString("standard_number"),
                        rs.getString("name"),
                        rs.getBoolean("isApproval"),
                        rs.getDouble("total_amount"),
                        rs.getString("creator"),
                        rs.getString("reviewer"),
                        rs.getString("status"),
                        rs.getString("created_at"),
                        null // Assuming salaryItems not loaded here
                );
                salaryStandards.add(salaryStandard);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryStandards;
    }

    @Override
    public SalaryStandard getById(int id) {
        String sql = "SELECT * FROM salary_standards WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new SalaryStandard(
                        rs.getInt("id"),
                        rs.getString("standard_number"),
                        rs.getString("name"),
                        rs.getBoolean("isApproval"),
                        rs.getDouble("total_amount"),
                        rs.getString("creator"),
                        rs.getString("reviewer"),
                        rs.getString("status"),
                        rs.getString("created_at"),
                        null // Assuming salaryItems not loaded here
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalaryStandard> getByName(String name) {
        List<SalaryStandard> salaryStandards = new ArrayList<>();
        String sql = "SELECT * FROM salary_standards WHERE name LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                salaryStandards.add(new SalaryStandard(
                        rs.getInt("id"),
                        rs.getString("standard_number"),
                        rs.getString("name"),
                        rs.getBoolean("isApproval"),
                        rs.getDouble("total_amount"),
                        rs.getString("creator"),
                        rs.getString("reviewer"),
                        rs.getString("status"),
                        rs.getString("created_at"),
                        null // Assuming salaryItems not loaded here
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaryStandards;
    }

}
