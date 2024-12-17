package com.hrms.service.impl;

import com.hrms.dao.DepartmentDAO;
import com.hrms.dao.impl.DepartmentDAOImpl;
import com.hrms.model.Department;
import com.hrms.service.DepartmentService;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {
        this.departmentDAO = new DepartmentDAOImpl();
    }



  @Override
public void addDepartment(Department department) throws Exception {
    validateDepartment(department);

    // 检查同级部门是否存在同名部门
    List<Department> sameLevelDepts = departmentDAO.findByLevel(department.getLevel());
    for (Department dept : sameLevelDepts) {
        if (dept.getName().equals(department.getName())) {
            throw new Exception("同级部门已存在同名部门：" + department.getName());
        }
    }

    // 插入部门并获取生成的ID
    int generatedId = departmentDAO.insert(department);
    department.setId(generatedId);
}

    @Override
    public void updateDepartment(Department department) throws Exception {
        // 验证部门信息
        validateDepartment(department);

        // 检查部门是否存在
        Department existingDept = getDepartmentById(department.getId());
        if (existingDept == null) {
            throw new Exception("部门不存在：ID " + department.getId());
        }

        // 检查是否有员工
        if (hasDepartmentEmployees(department.getId())) {
            throw new Exception("该部门下存在员工，无法更新");
        }

        // 检查同级部门是否存在同名部门(排除自身)
        List<Department> sameLevelDepts = departmentDAO.findByLevel(department.getLevel());
        for (Department dept : sameLevelDepts) {
            if (dept.getId() != department.getId() && dept.getName().equals(department.getName())) {
                throw new Exception("同级部门已存在同名部门：" + department.getName());
            }
        }

        departmentDAO.update(department);
    }

    @Override
    public void deleteDepartment(int id) throws Exception {
        Department department = getDepartmentById(id);
        if (department == null) {
            throw new Exception("部门不存在：ID " + id);
        }

        // 检查是否有员工
        if (hasDepartmentEmployees(id)) {
            throw new Exception("该部门下存在员工，无法删除");
        }

        // 检查是否有子部门
        List<Department> subDepts = departmentDAO.findByParentId(id);
        if (!subDepts.isEmpty()) {
            throw new Exception("该部门下存在子部门，无法删除");
        }

        departmentDAO.delete(id);
    }

    @Override
    public Department getDepartmentById(int id) throws Exception {
        return departmentDAO.findById(id);
    }

 @Override
public List<Department> getAllDepartments() throws Exception {
    try {
        List<Department> departments = departmentDAO.findAll();
        System.out.println("Service层获取到的部门数量: " + departments.size());
        departments.forEach(dept ->
            System.out.println("部门信息: ID=" + dept.getId() +
                             ", Name=" + dept.getName() +
                             ", Level=" + dept.getLevel()));
        return departments;
    } catch (Exception e) {
        e.printStackTrace();
        throw new Exception("获取部门列表失败", e);
    }
}

    @Override
    public List<Department> getDepartmentsByLevel(int level) throws Exception {
        if (level < 1 || level > 3) {
            throw new Exception("部门层级必须在1-3之间");
        }
        return departmentDAO.findByLevel(level);
    }

    @Override
    public boolean hasDepartmentEmployees(int departmentId) throws Exception {
        return departmentDAO.countEmployeesByDepartment(departmentId) > 0;
    }

    // 私有辅助方法
    private void validateDepartment(Department department) throws Exception {
        if (department == null) {
            throw new Exception("部门信息不能为空");
        }
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            throw new Exception("部门名称不能为空");
        }
        if (department.getLevel() < 1 || department.getLevel() > 3) {
            throw new Exception("部门层级必须在1-3之间");
        }
        if (department.getLevel() > 1 && department.getParentId() <= 0) {
            throw new Exception("非一级部门必须指定上级部门");
        }
    }
}