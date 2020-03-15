package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    String findDepartmentNameById(int dpId);

    int insertDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(int dpId);
}
