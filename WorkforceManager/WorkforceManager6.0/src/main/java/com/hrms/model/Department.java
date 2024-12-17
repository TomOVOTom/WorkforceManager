package com.hrms.model;

public class Department {
    private int id;
    private String name;
    private int parentId = 0;  // 默认无上级部门
    private int level = 1;     // 默认一级部门
    private int status = 1;    // 默认正常状态
    private String createdAt;    // 创建时间private int id;


    // 构造函数
    public Department() {}

    public Department(String name, int parentId, int level) {
        this.name = name;
        this.parentId = parentId;
        this.level = level;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // getter和setter方法
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}