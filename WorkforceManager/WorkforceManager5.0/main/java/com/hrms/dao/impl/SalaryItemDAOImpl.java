package com.hrms.dao.impl;

import com.hrms.dao.SalaryItemDAO;
import com.hrms.model.SalaryItem;
import com.hrms.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalaryItemDAOImpl implements SalaryItemDAO {
    @Override
    public boolean add(SalaryItem salaryItem) {
        String sql = "INSERT INTO salary_items (ItemId, ItemName, Calculation, CanUse, Creator, CreateTime, UpdateTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryItem.getItemId());
            pstmt.setString(2, salaryItem.getItemName());
            pstmt.setString(3, salaryItem.getCalculation());
            pstmt.setBoolean(4, salaryItem.isCanUse());
            pstmt.setString(5, salaryItem.getCreator());
            pstmt.setTimestamp(6, Timestamp.valueOf(salaryItem.getCreateTime()));
            pstmt.setTimestamp(7, Timestamp.valueOf(salaryItem.getUpdateTime()));

            return pstmt.executeUpdate() > 0;  // 插入数据并返回是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(SalaryItem salaryItem) {
        String sql = "UPDATE salary_items SET ItemName = ?, Calculation = ?, CanUse = ?, UpdateTime = ? WHERE ItemId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryItem.getItemName());
            pstmt.setString(2, salaryItem.getCalculation());
            pstmt.setBoolean(3, salaryItem.isCanUse());
            pstmt.setTimestamp(4, Timestamp.valueOf(salaryItem.getUpdateTime()));
            pstmt.setString(5, salaryItem.getItemId());

            return pstmt.executeUpdate() > 0;  //
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String salaryItemId) {
        String sql = "DELETE FROM salary_items WHERE ItemId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean enable(String salaryItemId) {
        String sql = "UPDATE salary_items SET CanUse = TRUE, UpdateTime = ? WHERE ItemId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(2, salaryItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean disable(String salaryItemId) {
        String sql = "UPDATE salary_items SET CanUse = FALSE, UpdateTime = ? WHERE ItemId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(2, salaryItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SalaryItem getById(String salaryItemId) {
        String sql = "SELECT * FROM salary_items WHERE ItemId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salaryItemId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapToSalaryItem(rs);
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SalaryItem> getAllSalaryItems() {
        String sql = "SELECT * FROM salary_items";
        List<SalaryItem> salaryItems = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                salaryItems.add(mapToSalaryItem(rs));
            }
            return salaryItems;

        } catch (SQLException e) {
            e.printStackTrace();
            return salaryItems;
        }
    }

    @Override
    public List<SalaryItem> getSalaryItemsByName(String itemName) {
        String sql = "SELECT * FROM salary_items WHERE ItemName LIKE ?";
        List<SalaryItem> salaryItems = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + itemName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                salaryItems.add(mapToSalaryItem(rs));
            }
            return salaryItems;

        } catch (SQLException e) {
            e.printStackTrace();
            return salaryItems;
        }
    }

    private SalaryItem mapToSalaryItem(ResultSet rs) throws SQLException {
        SalaryItem salaryItem = new SalaryItem();
        salaryItem.setItemId(rs.getString("ItemId"));
        salaryItem.setItemName(rs.getString("ItemName"));
        salaryItem.setCalculation(rs.getString("Calculation"));
        salaryItem.setCanUse(rs.getBoolean("CanUse"));
        salaryItem.setCreator(rs.getString("Creator"));
        salaryItem.setCreateTime(rs.getTimestamp("CreateTime").toLocalDateTime());
        salaryItem.setUpdateTime(rs.getTimestamp("UpdateTime").toLocalDateTime());
        return salaryItem;
    }
}
