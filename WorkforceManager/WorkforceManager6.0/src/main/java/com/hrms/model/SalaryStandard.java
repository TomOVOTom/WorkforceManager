package com.hrms.model;

import java.io.Serializable;
import java.util.List;

public class SalaryStandard implements Serializable {
    private int id;
    private String standardNumber;
    private String name;
    private boolean is_approval;
    @Override
    public String toString() {
        return "SalaryStandard{" +
                "id=" + id +
                ", standardNumber='" + standardNumber + '\'' +
                ", name='" + name + '\'' +
                ", is_approval=" + is_approval +
                ", totalAmount=" + totalAmount +
                ", creator='" + creator + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", SalaryItems=" + SalaryItems +
                '}';
    }

    private double totalAmount;
    private String creator;
    private String reviewer;
    private String status;
    private boolean isActive;

    public SalaryStandard(int id, String standardNumber, String name, boolean is_approval, double totalAmount, String creator, String reviewer, String status, String createTime, List<SalaryItem> salaryItems) {
        this.id = id;
        this.standardNumber = standardNumber;
        this.name = name;
        this.is_approval = is_approval;
        this.totalAmount = totalAmount;
        this.creator = creator;
        this.reviewer = reviewer;
        this.status = status;
        this.createTime = createTime;
        SalaryItems = salaryItems;
    }

    public boolean isIs_approval() {
        return is_approval;
    }

    public void setIs_approval(boolean is_approval) {
        this.is_approval = is_approval;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<SalaryItem> getSalaryItems() {
        return SalaryItems;
    }

    public void setSalaryItems(List<SalaryItem> salaryItems) {
        SalaryItems = salaryItems;
    }

    private String createTime;
    private List<SalaryItem> SalaryItems;

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


    // 其他getter和setter方法
    // ...
}