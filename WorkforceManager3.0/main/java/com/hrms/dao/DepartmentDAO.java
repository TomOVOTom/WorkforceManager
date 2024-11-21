package com.hrms.dao;

import com.hrms.model.Department;
import java.util.List;

public interface DepartmentDAO {
    void insert(Department department) throws Exception;
    void update(Department department) throws Exception;
    void delete(int id) throws Exception;
    Department findById(int id) throws Exception;
    List<Department> findAll() throws Exception;
    List<Department> findByLevel(int level) throws Exception;
    List<Department> findByParentId(int parentId) throws Exception;
    int countEmployeesByDepartment(int departmentId) throws Exception;
}