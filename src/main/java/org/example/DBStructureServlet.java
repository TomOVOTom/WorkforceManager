package org.example;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/DBStructure")
public class DBStructureServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>数据库结构测试</title>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("</style>");
        out.println("</head><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hrms?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root",
                    "12345678"
            );

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables("hrms", null, "%", new String[] {"TABLE"});

            out.println("<h2>数据库表结构：</h2>");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (tableName.equals("employees") ||
                        tableName.equals("salary_records") ||
                        tableName.equals("attendance_records")) {

                    out.println("<h3>" + tableName + "</h3>");
                    out.println("<table>");
                    out.println("<tr><th>列名</th><th>数据类型</th><th>约束</th></tr>");

                    Set<String> processedColumns = new HashSet<>();
                    ResultSet columns = metaData.getColumns(null, null, tableName, null);
                    while (columns.next()) {
                        String columnName = columns.getString("COLUMN_NAME");
                        if (!processedColumns.contains(columnName)) {
                            processedColumns.add(columnName);
                            String columnType = columns.getString("TYPE_NAME");
                            String nullable = columns.getString("IS_NULLABLE");
                            List<String> constraints = new ArrayList<>();

                            if (nullable.equals("NO")) {
                                constraints.add("必填");
                            }

                            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                            while (primaryKeys.next()) {
                                if (primaryKeys.getString("COLUMN_NAME").equals(columnName)) {
                                    constraints.add("主键");
                                }
                            }

                            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
                            while (foreignKeys.next()) {
                                if (foreignKeys.getString("FKCOLUMN_NAME").equals(columnName)) {
                                    constraints.add("外键 -> " + foreignKeys.getString("PKTABLE_NAME"));
                                }
                            }

                            out.println("<tr>");
                            out.println("<td>" + columnName + "</td>");
                            out.println("<td>" + columnType + "</td>");
                            out.println("<td>" + String.join(", ", constraints) + "</td>");
                            out.println("</tr>");
                        }
                    }
                    out.println("</table>");
                }
            }

            conn.close();

        } catch (Exception e) {
            out.println("<h2 style='color:red'>错误：</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}