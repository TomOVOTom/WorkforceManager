package com.hrms.service;

import com.hrms.model.SalaryStandard;

import java.util.List;

public interface SalaryStandardService {

    boolean addSalaryStandard(SalaryStandard salaryStandard);

    boolean updateSalaryStandard(SalaryStandard salaryStandard);

    boolean deleteSalaryStandard(int id);

    List<SalaryStandard> getAllSalaryStandards();

    SalaryStandard getSalaryStandardById(int id);

    List<SalaryStandard> getSalaryStandardByName(String name);
}
