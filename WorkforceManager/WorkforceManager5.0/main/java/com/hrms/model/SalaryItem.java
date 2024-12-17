package com.hrms.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SalaryItem implements Serializable {
    private String ItemId;
    private String ItemName;
    private String Calculation;
    private boolean CanUse;
    private String Creator;
    private LocalDateTime CreateTime;
    private LocalDateTime UpdateTime;

    public SalaryItem(String itemId, String itemName, String calculation, boolean canUse, String creator, LocalDateTime createTime, LocalDateTime updateTime) {
        ItemId = itemId;
        ItemName = itemName;
        Calculation = calculation;
        CanUse = canUse;
        Creator = creator;
        CreateTime = createTime;
        UpdateTime = updateTime;
    }

    public SalaryItem() {
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getCalculation() {
        return Calculation;
    }

    public void setCalculation(String calculation) {
        Calculation = calculation;
    }

    public boolean isCanUse() {
        return CanUse;
    }

    @Override
    public String toString() {
        return "SalaryItem{" +
                "ItemId='" + ItemId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Calculation='" + Calculation + '\'' +
                ", CanUse=" + CanUse +
                ", Creator='" + Creator + '\'' +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                '}';
    }

    public void setCanUse(boolean canUse) {
        CanUse = canUse;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        CreateTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        UpdateTime = updateTime;
    }
}
