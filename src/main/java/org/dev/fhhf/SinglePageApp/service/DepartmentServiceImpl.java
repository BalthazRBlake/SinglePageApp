package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.DepartmentDao;
import org.dev.fhhf.SinglePageApp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public String findDepartmentNameById(int dpId) {
        try{
            String dpName = departmentDao.findDepartmentNameById(dpId);
            return dpName;
        }
        catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            return "";
        }
    }

    @Override
    public int insertDepartment(Department department) {
        try{
            return departmentDao.insertDepartment(department);
        }
        catch (DataIntegrityViolationException ex){
            return 0;
        }
    }

    @Override
    public int updateDepartment(Department department) {
        try{
            return departmentDao.updateDepartment(department);
        }
        catch (DataIntegrityViolationException ex){
            return 0;
        }
    }

    @Override
    public int deleteDepartment(int dpId) {
        try{
            return departmentDao.deleteDepartment(dpId);
        }
        catch (DataIntegrityViolationException ex){
            return 0;
        }
    }
}
