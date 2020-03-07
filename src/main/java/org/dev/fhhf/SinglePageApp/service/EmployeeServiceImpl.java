package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.EmployeeDao;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int countTotalEmployees() {
        return employeeDao.countTotalEmployees();
    }

    @Override
    public Employee findEmployeeById(int empId){

        Employee employee = employeeDao.findEmployeeById(empId);

        return employee;
    }
}
