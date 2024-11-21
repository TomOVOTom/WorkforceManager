package com.hrms.model;

public class Position {
    private int id;
    private String name;
    private int departmentId;
    private String description;
    private int status;
    private String createTime;

    // 构造函数
    public Position() {}

    public Position(String name, int departmentId, String description) {
        this.name = name;
        this.departmentId = departmentId;
        this.description = description;
        this.status = 1;
    }

    // getter和setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}