package com.hrms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.model.Employee;
import com.hrms.model.SalaryStandard;
import com.hrms.service.SalaryStandardService;
import com.hrms.service.impl.SalaryStandardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/salary/*")
public class SalaryStandardServlet extends HttpServlet {

    private final SalaryStandardServiceImpl salaryStandardService;
    private final SimpleDateFormat dateFormat;

    public SalaryStandardServlet() {
        this.salaryStandardService = new SalaryStandardServiceImpl();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo.equals("/add")) {
                // 处理新增薪酬标准
                addSalaryStandard(request, response);
                response.sendRedirect(request.getContextPath() + "/salary/");
            } else if (pathInfo.equals("/update")) {
                // 处理更新薪酬标准
//                (request);
                response.sendRedirect(request.getContextPath() + "/salary/");
            } else if (pathInfo.equals("/delete")) {
                // 处理删除员工
                deleteSalaryStandard(request, response);
                response.sendRedirect(request.getContextPath() + "/salary/");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理查询薪酬标准
        String pathInfo = request.getPathInfo();
        try {
            // 路径非空时才进行后续判断
            if (pathInfo != null || pathInfo.equals("/")) {
                switch (pathInfo) {
                    case "/":
                        listSalaryStandards(request, response);
                        request.getRequestDispatcher("/WEB-INF/views/salary/list.jsp").forward(request, response);
                        break;
                    case "/add":
                        request.getRequestDispatcher("/WEB-INF/views/salary/add-content.jsp").forward(request, response);
                        break;
                }
            } else if (pathInfo.startsWith("/view/")){
                // 查看员工详情
                int id = Integer.parseInt(pathInfo.substring(6));
                SalaryStandard salaryStandard = salaryStandardService.getSalaryStandardById(id);
                if (salaryStandard != null) {
                    request.setAttribute("standard", salaryStandard);
                    request.getRequestDispatcher("/WEB-INF/views/salary/view.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else if (pathInfo.startsWith("/edit/")) {
                // 显示编辑员工表单
                int id = Integer.parseInt(pathInfo.substring(6));
                SalaryStandard standard = salaryStandardService.getSalaryStandardById(id);
                if (standard != null) {
                    request.setAttribute("standard", standard);
                    request.getRequestDispatcher("/WEB-INF/views/salary/edit.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Path information missing");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理删除薪酬标准
        String pathInfo = request.getPathInfo();

        // 确保 pathInfo 不为 null，然后再进行路径判断
        if (pathInfo != null && pathInfo.equals("/delete")) {
            deleteSalaryStandard(request, response);
        } else {
            // 如果路径不符合要求，可以返回错误或其他处理
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path for DELETE request");
        }
    }

    // 新增薪酬标准
    private void addSalaryStandard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String standardNumber = request.getParameter("standardNumber");
        String name = request.getParameter("name");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        String creator = request.getParameter("creator");
        String reviewer = request.getParameter("reviewer");
        String status = request.getParameter("status");
        String createTime = request.getParameter("createTime");


        // 创建薪酬标准对象
        SalaryStandard salaryStandard = new SalaryStandard();
        salaryStandard.setStandardNumber(standardNumber);
        salaryStandard.setName(name);
        salaryStandard.setTotalAmount(totalAmount);
        salaryStandard.setCreator(creator);
        salaryStandard.setReviewer(reviewer);
        salaryStandard.setStatus(status);
        salaryStandard.setCreateTime(createTime);

        // 调用 service 层方法进行添加
        boolean isAdded = salaryStandardService.addSalaryStandard(salaryStandard);

        // 根据操作结果返回响应
        if (isAdded) {
            response.getWriter().write("Salary Standard added successfully");
        } else {
            response.getWriter().write("Failed to add Salary Standard");
        }
    }

    // 查询所有薪酬标准
    private void listSalaryStandards(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<SalaryStandard> salaryStandards = salaryStandardService.getAllSalaryStandards();
        request.setAttribute("salarystandards", salaryStandards);
    }

    // 删除薪酬标准
    private void deleteSalaryStandard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        boolean isDeleted = salaryStandardService.deleteSalaryStandard(Integer.parseInt(id));

        if (isDeleted) {
            response.getWriter().write("Salary Standard deleted successfully");
        } else {
            response.getWriter().write("Failed to delete Salary Standard");
        }
    }
}
