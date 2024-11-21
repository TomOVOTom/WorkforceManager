package com.hrms.service;

import com.hrms.model.Employee;
import java.util.List;

public interface EmployeeService {
    // 基本CRUD操作
    void addEmployee(Employee employee) throws Exception;
    void updateEmployee(Employee employee) throws Exception;
    void deleteEmployee(int id) throws Exception;
    Employee getEmployeeById(int id) throws Exception;
    Employee getEmployeeByNumber(String employeeNumber) throws Exception;
    List<Employee> getAllEmployees() throws Exception;

    // 业务相关操作
    List<Employee> getEmployeesByDepartment(String department) throws Exception;
    boolean isEmployeeNumberExists(String employeeNumber) throws Exception;
    void updateEmployeeSalary(int id, double newSalary) throws Exception;
    void updateEmployeeStatus(int id, String newStatus) throws Exception;
}