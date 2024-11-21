package com.hrms.controller;

import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;
import com.hrms.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/employee/*")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeService employeeService;
    private final SimpleDateFormat dateFormat;

    public EmployeeServlet() {
        this.employeeService = new EmployeeServiceImpl();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // 获取所有员工列表
                List<Employee> employees = employeeService.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(request, response);
            } else if (pathInfo.equals("/add")) {
                // 显示添加员工表单
                request.getRequestDispatcher("/WEB-INF/views/employee/add.jsp").forward(request, response);
            } else if (pathInfo.startsWith("/edit/")) {
                // 显示编辑员工表单
                int id = Integer.parseInt(pathInfo.substring(6));
                Employee employee = employeeService.getEmployeeById(id);
                if (employee != null) {
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("/WEB-INF/views/employee/edit.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else if (pathInfo.startsWith("/view/")) {
                // 查看员工详情
                int id = Integer.parseInt(pathInfo.substring(6));
                Employee employee = employeeService.getEmployeeById(id);
                if (employee != null) {
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("/WEB-INF/views/employee/view.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo.equals("/add")) {
                // 处理添加员工
                Employee employee = extractEmployeeFromRequest(request);
                employeeService.addEmployee(employee);
                response.sendRedirect(request.getContextPath() + "/employee/");
            } else if (pathInfo.equals("/update")) {
                // 处理更新员工
                Employee employee = extractEmployeeFromRequest(request);
                employeeService.updateEmployee(employee);
                response.sendRedirect(request.getContextPath() + "/employee/");
            } else if (pathInfo.equals("/delete")) {
                // 处理删除员工
                int id = Integer.parseInt(request.getParameter("id"));
                employeeService.deleteEmployee(id);
                response.sendRedirect(request.getContextPath() + "/employee/");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private Employee extractEmployeeFromRequest(HttpServletRequest request) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = new Employee();

        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            employee.setId(Integer.parseInt(request.getParameter("id")));
        }

        employee.setEmployeeNumber(request.getParameter("employeeNumber"));
        employee.setName(request.getParameter("name"));
        employee.setGender(request.getParameter("gender"));
        employee.setIdCard(request.getParameter("idCard"));
        employee.setPhone(request.getParameter("phone"));
        employee.setEmail(request.getParameter("email"));
        employee.setAddress(request.getParameter("address"));
        employee.setDepartment(request.getParameter("department"));
        employee.setPosition(request.getParameter("position"));

        // 设置日期
        String birthDateStr = request.getParameter("birthDate");
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            employee.setBirthDate(dateFormat.parse(birthDateStr));
        }

        String hireDateStr = request.getParameter("hireDate");
        if (hireDateStr != null && !hireDateStr.isEmpty()) {
            employee.setHireDate(dateFormat.parse(hireDateStr));
        }

        // 添加缺失的字段
        employee.setStatus(request.getParameter("status"));
        String salaryStr = request.getParameter("salary");
        if (salaryStr != null && !salaryStr.isEmpty()) {
            employee.setSalary(Double.parseDouble(salaryStr));
        }

        return employee;
    }
}