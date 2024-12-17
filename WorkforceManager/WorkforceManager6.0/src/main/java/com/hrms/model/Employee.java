package com.hrms.model;

import java.util.Date;

public class Employee {
    private int id;
    private String employeeNumber;
    private String name;
    private String gender;
    private Date birthDate;
    private String idCard;
    private String phone;
    private String email;
    private String address;
    private String department;
    private String position;
    private Date hireDate;
    private String status;
    private double salary;

    // 构造函数
    public Employee() {}

    public Employee(String employeeNumber, String name, String gender,
                    Date birthDate, String idCard, String phone, String email,
                    String address, String department, String position,
                    Date hireDate, String status, double salary) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.idCard = idCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.position = position;
        this.hireDate = hireDate;
        this.status = status;
        this.salary = salary;
    }

    // Getter和Setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}