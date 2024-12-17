package com.hrms.service.impl;

import com.hrms.dao.SalaryItemDAO;
import com.hrms.dao.impl.SalaryItemDAOImpl;
import com.hrms.model.SalaryItem;
import com.hrms.service.SalaryItemService;

import java.util.List;

public class SalaryItemServiceImpl implements SalaryItemService {
    private SalaryItemDAO salaryItemDAO;

    // 构造器，注入DAO实例
    public SalaryItemServiceImpl() {
        this.salaryItemDAO = new SalaryItemDAOImpl();
    }

    @Override
    public boolean addSalaryItem(SalaryItem salaryItem) {
        return salaryItemDAO.add(salaryItem);
    }

    @Override
    public boolean updateSalaryItem(SalaryItem salaryItem) {
        return salaryItemDAO.update(salaryItem);
    }

    @Override
    public boolean deleteSalaryItem(String salaryItemId) {
        return salaryItemDAO.delete(salaryItemId);
    }

    @Override
    public boolean enableSalaryItem(String salaryItemId) {
        return salaryItemDAO.enable(salaryItemId);
    }

    @Override
    public boolean disableSalaryItem(String salaryItemId) {
        return salaryItemDAO.disable(salaryItemId);
    }

    @Override
    public SalaryItem getSalaryItemById(String salaryItemId) {
        return salaryItemDAO.getById(salaryItemId);
    }

    @Override
    public List<SalaryItem> getAllSalaryItems() {
        return salaryItemDAO.getAllSalaryItems();
    }

    @Override
    public List<SalaryItem> getSalaryItemsByName(String itemName) {
        return salaryItemDAO.getSalaryItemsByName(itemName);
    }
}
