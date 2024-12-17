package com.hrms.service;

import com.hrms.model.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department) throws Exception;
    void updateDepartment(Department department) throws Exception;
    void deleteDepartment(int id) throws Exception;
    Department getDepartmentById(int id) throws Exception;
    List<Department> getAllDepartments() throws Exception;
    List<Department> getDepartmentsByLevel(int level) throws Exception;
    boolean hasDepartmentEmployees(int departmentId) throws Exception;
}