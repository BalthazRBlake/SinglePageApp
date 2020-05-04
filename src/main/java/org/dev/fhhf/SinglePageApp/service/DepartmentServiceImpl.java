package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.DepartmentDao;
import org.dev.fhhf.SinglePageApp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findAllDepartments() {
        return departmentDao.findAllDepartments();
    }

    @Override
    public Department findDepartmentById(int dpId) {
        return departmentDao.findDepartmentById(dpId);
    }

    @Override
    public Department insertDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public int deleteDepartment(int dpId) {
        return departmentDao.deleteDepartment(dpId);
    }
}
