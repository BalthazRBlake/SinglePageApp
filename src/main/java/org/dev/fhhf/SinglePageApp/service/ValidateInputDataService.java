package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ValidateInputDataService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    public void validateEmployee(int empId){
        try{
            employeeService.findEmployeeById(empId);
        }catch (EmptyResultDataAccessException ex){
            throw new DataNotFoundException("Employee with id " + empId + " not found", ex);
        }
    }

    public void validateDepartment(int dpId){
        try{
            departmentService.findDepartmentById(dpId);
        }catch (EmptyResultDataAccessException ex) {
            throw new DataNotFoundException("Department with id " + dpId + " not found", ex);
        }
    }
}
