package com.hrms.dao;

import com.hrms.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    void insert(Employee employee) throws Exception;
    void update(Employee employee) throws Exception;
    void delete(int id) throws Exception;
    Employee findById(int id) throws Exception;
    List<Employee> findAll() throws Exception;
    List<Employee> findByDepartment(String department) throws Exception;
    Employee findByEmployeeNumber(String employeeNumber) throws Exception;
}