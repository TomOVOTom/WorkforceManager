package org.example;


import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DBTest")
public class DBConnectionTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>数据库连接测试</title></head><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hrms?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root",
                    "12345678"
            );

            out.println("<h2 style='color:green'>数据库连接成功！</h2>");
            conn.close();

        } catch (Exception e) {
            out.println("<h2 style='color:red'>数据库连接失败！</h2>");
            out.println("<p>错误信息：" + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}