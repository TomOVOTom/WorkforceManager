package com.hrms.controller;

import com.hrms.model.Department;
import com.hrms.service.DepartmentService;
import com.hrms.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/department/*")
public class DepartmentServlet extends HttpServlet {
    private final DepartmentService departmentService;

    public DepartmentServlet() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // 获取所有部门列表
                List<Department> departments = departmentService.getAllDepartments();
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/WEB-INF/views/department/list.jsp").forward(request, response);
            } else if (pathInfo.equals("/add")) {
                // 显示添加部门表单
                List<Department> parentDepartments = departmentService.getDepartmentsByLevel(1);
                request.setAttribute("parentDepartments", parentDepartments);
                request.getRequestDispatcher("/WEB-INF/views/department/add-content.jsp").forward(request, response);
            } else if (pathInfo.startsWith("/edit/")) {
                // 显示编辑部门表单
                int id = Integer.parseInt(pathInfo.substring(6));
                Department department = departmentService.getDepartmentById(id);
                if (department != null) {
                    List<Department> parentDepartments = departmentService.getDepartmentsByLevel(department.getLevel() - 1);
                    request.setAttribute("parentDepartments", parentDepartments);
                    request.setAttribute("department", department);
                    request.getRequestDispatcher("/WEB-INF/views/department/edit.jsp").forward(request, response);
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
                // 处理添加部门
                Department department = extractDepartmentFromRequest(request);
                departmentService.addDepartment(department);
                response.sendRedirect(request.getContextPath() + "/department/");
            } else if (pathInfo.equals("/update")) {
                // 处理更新部门
                Department department = extractDepartmentFromRequest(request);
                departmentService.updateDepartment(department);
                response.sendRedirect(request.getContextPath() + "/department/");
            } else if (pathInfo.equals("/delete")) {
                // 处理删除部门
                int id = Integer.parseInt(request.getParameter("id"));
                departmentService.deleteDepartment(id);
                response.sendRedirect(request.getContextPath() + "/department/");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

  private Department extractDepartmentFromRequest(HttpServletRequest request) {
    Department department = new Department();

    // 部门名称
    String name = request.getParameter("name");
    if (name != null && !name.trim().isEmpty()) {
        department.setName(name.trim());
    } else {
        throw new IllegalArgumentException("部门名称不能为空");
    }

    // 部门层级
    String levelStr = request.getParameter("level");
    if (levelStr != null && !levelStr.isEmpty()) {
        int level = Integer.parseInt(levelStr);
        if (level >= 1 && level <= 3) {
            department.setLevel(level);
        } else {
            throw new IllegalArgumentException("部门层级无效");
        }
    } else {
        department.setLevel(1); // 默认一级部门
    }

    // 上级部门ID
    String parentIdStr = request.getParameter("parentId");
    if (parentIdStr != null && !parentIdStr.isEmpty()) {
        int parentId = Integer.parseInt(parentIdStr);
        department.setParentId(parentId);
    } else {
        department.setParentId(0); // 默认无上级部门
    }

    // 状态
    String statusStr = request.getParameter("status");
    if (statusStr != null && !statusStr.isEmpty()) {
        department.setStatus(Integer.parseInt(statusStr));
    } else {
        department.setStatus(1); // 默认正常状态
    }

      // 创建时间
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      department.setCreatedAt(sdf.format(new Date())); // 将 Date 转换为格式化的字符串

    return department;
}

}