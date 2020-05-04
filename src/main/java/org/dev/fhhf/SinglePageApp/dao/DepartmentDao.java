package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> findAllDepartments();

    Department findDepartmentById(int dpId);

    int insertDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(int dpId);
}
