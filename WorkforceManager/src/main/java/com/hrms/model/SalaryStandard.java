package com.hrms.model;

public class SalaryStandard {
    private int id;
    private String standardNumber;
    private String name;
    private double basicSalary;
    private double pension;
    private double medical;
    private double unemployment;
    private double housingFund;
    private double totalAmount;
    private String creator;
    private String reviewer;
    private String status;
    private String createTime;

    // 构造函数
    public SalaryStandard() {}

    // getter和setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
        calculateInsurance();
    }

    // 计算五险一金
    private void calculateInsurance() {
        this.pension = basicSalary * 0.08;
        this.medical = basicSalary * 0.02 + 3;
        this.unemployment = basicSalary * 0.005;
        this.housingFund = basicSalary * 0.08;
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = basicSalary - pension - medical - unemployment - housingFund;
    }

    // 其他getter和setter方法
    // ...
}