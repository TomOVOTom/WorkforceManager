package com.hrms.service.impl;

import com.hrms.dao.EmployeeDAO;
import com.hrms.dao.impl.EmployeeDAOImpl;
import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {
        // 验证员工信息
        validateEmployee(employee);

        // 检查工号是否已存在
        if (isEmployeeNumberExists(employee.getEmployeeNumber())) {
            throw new Exception("员工工号已存在：" + employee.getEmployeeNumber());
        }

        employeeDAO.insert(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
        // 验证员工信息
        validateEmployee(employee);

        // 检查员工是否存在
        Employee existingEmployee = getEmployeeByNumber(employee.getEmployeeNumber());
        if (existingEmployee == null) {
            throw new Exception("员工不存在：" + employee.getEmployeeNumber());
        }

        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(int id) throws Exception {
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            throw new Exception("员工不存在：ID " + id);
        }
        employeeDAO.delete(id);
    }

    @Override
    public Employee getEmployeeById(int id) throws Exception {
        return employeeDAO.findById(id);
    }

    @Override
    public Employee getEmployeeByNumber(String employeeNumber) throws Exception {
        return employeeDAO.findByEmployeeNumber(employeeNumber);
    }

    @Override
    public List<Employee> getAllEmployees() throws Exception {
        return employeeDAO.findAll();
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) throws Exception {
        return employeeDAO.findByDepartment(department);
    }

    @Override
    public boolean isEmployeeNumberExists(String employeeNumber) throws Exception {
        return employeeDAO.findByEmployeeNumber(employeeNumber) != null;
    }

    @Override
    public void updateEmployeeSalary(int id, double newSalary) throws Exception {
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            throw new Exception("员工不存在：ID " + id);
        }
        if (newSalary < 0) {
            throw new Exception("薪资不能为负数");
        }
        employee.setSalary(newSalary);
        employeeDAO.update(employee);
    }

    @Override
    public void updateEmployeeStatus(int id, String newStatus) throws Exception {
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            throw new Exception("员工不存在：ID " + id);
        }
        if (!isValidStatus(newStatus)) {
            throw new Exception("无效的员工状态：" + newStatus);
        }
        employee.setStatus(newStatus);
        employeeDAO.update(employee);
    }

    // 私有辅助方法
    private void validateEmployee(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("员工信息不能为空");
        }
        if (employee.getEmployeeNumber() == null || employee.getEmployeeNumber().trim().isEmpty()) {
            throw new Exception("员工工号不能为空");
        }
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new Exception("员工姓名不能为空");
        }
        if (employee.getSalary() < 0) {
            throw new Exception("薪资不能为负数");
        }
        // 可以添加更多验证规则
    }

    private boolean isValidStatus(String status) {
        return status != null && (
                status.equals("在职") ||
                        status.equals("离职") ||
                        status.equals("休假")
        );
    }
}