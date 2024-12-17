package com.hrms.dao;

import com.hrms.model.SalaryItem;

import java.util.List;

public interface SalaryItemDAO {
    /**
     * 添加新的薪酬项目
     * @param salaryItem 薪酬项目对象
     * @return 是否添加成功
     */
    boolean add(SalaryItem salaryItem);

    /**
     * 更新现有的薪酬项目
     * @param salaryItem 薪酬项目对象
     * @return 是否更新成功
     */
    boolean update(SalaryItem salaryItem);

    /**
     * 根据ID删除薪酬项目
     * @param salaryItemId 薪酬项目ID
     * @return 是否删除成功
     */
    boolean delete(String salaryItemId);

    /**
     * 启用现有的薪酬项目
     * @param salaryItemId 薪酬项目ID
     * @return 是否启用成功
     */
    boolean enable(String salaryItemId);

    /**
     * 停用现有的薪酬项目
     * @param salaryItemId 薪酬项目ID
     * @return 是否成功
     */
    boolean disable(String salaryItemId);

    /**
     * 根据ID查询薪酬项目
     * @param salaryItemId 薪酬项目ID
     * @return 薪酬项目对象
     */
    SalaryItem getById(String salaryItemId);

    /**
     * 查询所有薪酬项目
     * @return 所有薪酬项目列表
     */
    List<SalaryItem> getAllSalaryItems();

    /**
     * 根据薪酬项目名称查询薪酬项目
     * @param itemName 薪酬项目名称
     * @return 薪酬项目列表
     */
    List<SalaryItem> getSalaryItemsByName(String itemName);
}
