package com.hrms.controller;

import com.hrms.model.SalaryStandard;
import com.hrms.service.impl.SalaryStandardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        String pathInfo = request.getPathInfo();
        try {
            // 1. 首先处理 pathInfo 为 null 或 "/" 的情况
            if (pathInfo == null || pathInfo.equals("/")) {
                listSalaryStandards(request, response);
                request.getRequestDispatcher("/WEB-INF/views/salary/list.jsp").forward(request, response);
                return;
            }

            // 2. 处理其他路径
            switch (pathInfo) {
                case "/add":
                    request.getRequestDispatcher("/WEB-INF/views/salary/add.jsp").forward(request, response);
                    break;

                default:
                    // 3. 处理带参数的路径
                    if (pathInfo.startsWith("/view/")) {
                        handleViewRequest(request, response, pathInfo);
                    } else if (pathInfo.startsWith("/edit/")) {
                        handleEditRequest(request, response, pathInfo);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid path: " + pathInfo);
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            // 处理 ID 解析错误
            request.setAttribute("error", "无效的ID格式");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            request.setAttribute("error", "系统错误：" + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    // 处理查看请求的私有方法
    private void handleViewRequest(HttpServletRequest request, HttpServletResponse response, String pathInfo)
            throws ServletException, IOException {
        try {
            // 提取ID并去除前面的"/view/"
            String idStr = pathInfo.substring(6);
            int id = Integer.parseInt(idStr);

            // 获取数据
            SalaryStandard standard = salaryStandardService.getSalaryStandardById(id);

            if (standard != null) {
                // 添加日志输出
                System.out.println("Found salary standard: " + standard.getName());
                request.setAttribute("standard", standard);
                request.getRequestDispatcher("/WEB-INF/views/salary/view.jsp").forward(request, response);
            } else {
                // 添加日志输出
                System.out.println("Salary standard not found for id: " + id);
                request.setAttribute("error", "未找到ID为 " + id + " 的薪酬标准");
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid ID format in URL: " + pathInfo);
        }
    }

    // 处理编辑请求的私有方法
    private void handleEditRequest(HttpServletRequest request, HttpServletResponse response, String pathInfo)
            throws ServletException, IOException {
        try {
            // 提取ID并去除前面的"/edit/"
            String idStr = pathInfo.substring(6);
            int id = Integer.parseInt(idStr);

            SalaryStandard standard = salaryStandardService.getSalaryStandardById(id);
            if (standard != null) {
                request.setAttribute("standard", standard);
                request.getRequestDispatcher("/WEB-INF/views/salary/edit.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "未找到ID为 " + id + " 的薪酬标准");
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid ID format in URL: " + pathInfo);
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
