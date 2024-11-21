package com.hrms.model;

public class Department {
    private int id;
    private String name;
    private int parentId;
    private int level;
    private int status;
    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}