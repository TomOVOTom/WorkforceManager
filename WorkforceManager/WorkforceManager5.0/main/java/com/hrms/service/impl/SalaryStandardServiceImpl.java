package com.hrms.service.impl;

import com.hrms.dao.impl.SalaryStandardDAOImpl;
import com.hrms.dao.SalaryStandardDAO;
import com.hrms.model.SalaryStandard;
import com.hrms.service.SalaryStandardService;

import java.util.List;

public class SalaryStandardServiceImpl implements SalaryStandardService {
    private final SalaryStandardDAO salaryStandardDAO;

    // 构造器，注入DAO实例
    public SalaryStandardServiceImpl() {
        this.salaryStandardDAO = new SalaryStandardDAOImpl();
    }

    @Override
    public boolean addSalaryStandard(SalaryStandard salaryStandard) {
        return salaryStandardDAO.add(salaryStandard);
    }

    @Override
    public boolean updateSalaryStandard(SalaryStandard salaryStandard) {
        return salaryStandardDAO.update(salaryStandard);
    }

    @Override
    public boolean deleteSalaryStandard(int id) {
        return salaryStandardDAO.delete(id);
    }

    @Override
    public List<SalaryStandard> getAllSalaryStandards() {
        return salaryStandardDAO.getAllSalaryStandards();
    }

    @Override
    public SalaryStandard getSalaryStandardById(int id) {
        return salaryStandardDAO.getById(id);
    }

    @Override
    public List<SalaryStandard> getSalaryStandardByName(String name) {
        return salaryStandardDAO.getByName(name);
    }
}
