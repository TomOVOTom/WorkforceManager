package com.hrms.dao;

import com.hrms.model.SalaryStandard;

import java.util.List;

public interface SalaryStandardDAO {
    // 添加薪酬标准
    boolean add(SalaryStandard salaryStandard);

    // 更新薪酬标准
    boolean update(SalaryStandard salaryStandard);

    // 删除薪酬标准
    boolean delete(int id);

//    boolean alterStatus(int id, String status);

    // 查询所有薪酬标准
    List<SalaryStandard> getAllSalaryStandards();

    // 根据薪酬标准ID查询
    SalaryStandard getById(int id);

    // 根据薪酬标准名称查询
    List<SalaryStandard> getByName(String name);
}
