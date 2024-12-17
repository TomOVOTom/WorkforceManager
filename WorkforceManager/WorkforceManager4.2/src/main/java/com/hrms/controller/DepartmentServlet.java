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
import java.util.stream.Collectors;

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
    try {
        // 设置响应编码
        response.setCharacterEncoding("UTF-8");

        // 获取所有部门列表
        List<Department> departments = departmentService.getAllDepartments();
        System.out.println("==================== DEBUG INFO ====================");
        System.out.println("部门总数: " + departments.size());
        System.out.println("所有部门信息:");
        for (Department dept : departments) {
            String info = String.format("    部门: [ID=%d, 名称=%s, 层级=%d]",
                dept.getId(), dept.getName(), dept.getLevel());
            System.out.println(new String(info.getBytes("UTF-8"), "UTF-8"));
        }

        // 过滤出可作为上级部门的部门（层级小于3的部门）
        List<Department> availableParents = departments.stream()
            .filter(dept -> dept.getLevel() < 3)
            .collect(Collectors.toList());

        System.out.println("\n可用作上级部门数量: " + availableParents.size());
        System.out.println("可用作上级部门的列表:");
        for (Department dept : availableParents) {
            String info = String.format("    部门: [ID=%d, 名称=%s, 层级=%d]",
                dept.getId(), dept.getName(), dept.getLevel());
            System.out.println(new String(info.getBytes("UTF-8"), "UTF-8"));
        }
        System.out.println("==================================================");

        request.setAttribute("departments", availableParents);
        request.getRequestDispatcher("/WEB-INF/views/department/add.jsp").forward(request, response);
    } catch (Exception e) {
        System.out.println("==================== ERROR INFO ====================");
        System.out.println("错误信息: " + e.getMessage());
        e.printStackTrace();
        System.out.println("==================================================");
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
    }
} else if (pathInfo.startsWith("/edit/")) {
            // 显示编辑部门表单
            int id = Integer.parseInt(pathInfo.substring(6));
            Department department = departmentService.getDepartmentById(id);
            if (department != null) {
                // 获取所有可作为上级部门的部门列表
                List<Department> departments = departmentService.getAllDepartments();
                List<Department> availableParents = departments.stream()
                    .filter(dept -> dept.getLevel() < 3 && dept.getId() != id)
                    .collect(Collectors.toList());

                request.setAttribute("departments", availableParents);
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
    int level;
    if (levelStr != null && !levelStr.isEmpty()) {
        level = Integer.parseInt(levelStr);
        if (level >= 1 && level <= 3) {
            department.setLevel(level);
        } else {
            throw new IllegalArgumentException("部门层级必须在1-3之间");
        }
    } else {
        throw new IllegalArgumentException("部门层级不能为空");
    }

    // 上级部门ID
    String parentIdStr = request.getParameter("parentId");
    int parentId = 0;
    if (parentIdStr != null && !parentIdStr.isEmpty()) {
        parentId = Integer.parseInt(parentIdStr);
    }

    // 验证非一级部门必须指定上级部门
    if (level > 1 && parentId == 0) {
        throw new IllegalArgumentException("非一级部门必须指定上级部门");
    }
    department.setParentId(parentId);

    // 状态
    String statusStr = request.getParameter("status");
    if (statusStr != null && !statusStr.isEmpty()) {
        department.setStatus(Integer.parseInt(statusStr));
    } else {
        department.setStatus(1); // 默认正常状态
    }

    // 创建时间
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    department.setCreatedAt(sdf.format(new Date()));

    // ID (用于更新操作)
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.isEmpty()) {
        department.setId(Integer.parseInt(idStr));
    }

    return department;
}

}