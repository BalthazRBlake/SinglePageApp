package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.EmployeeDao;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Employee> findAllEmployeesPaginated(int page, int size) {
        return employeeDao.findAllEmployeesPaginated(page, size);
    }

    @Override
    public List<Employee> findEmployeesNameStartsWith(String empName, int page, int size) {
        return employeeDao.findEmployeesNameStartsWith(empName, page, size);
    }

    @Override
    public Employee findEmployeeById(int empId){
        try {
            return employeeDao.findEmployeeById(empId);
        }
        catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            return new Employee();
        }
    }

    @Override
    public int insertEmployee(Employee employee) {
        try {
            return employeeDao.insertEmployee(employee);
        }
        catch (DataIntegrityViolationException ex){
            return 0;
        }
    }

    @Override
    public int updateEmployee(Employee employee) {
        try{
            return employeeDao.updateEmployee(employee);
        }
        catch (DataIntegrityViolationException ex){
            return 0;
        }
    }
}
