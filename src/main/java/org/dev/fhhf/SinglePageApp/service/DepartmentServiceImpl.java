package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.DepartmentDao;
import org.dev.fhhf.SinglePageApp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

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
}
