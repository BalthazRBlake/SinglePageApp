package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.EmployeeDao;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int countTotalEmployees() {
        try{
            return employeeDao.countTotalEmployees();
        } catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public Employee findEmployeeById(int empId){
        try {
            Employee employee = employeeDao.findEmployeeById(empId);
            return employee;
        }
        catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            return new Employee();
        }
    }
}
