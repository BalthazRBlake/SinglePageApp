package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.dao.EmployeeDao;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int countTotalEmployees() {
        return employeeDao.countTotalEmployees();
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
    public List<Employee> findEmployeesNameStartsWith(String empName) {
        return employeeDao.findEmployeesNameStartsWith(empName);
    }

    @Override
    public Employee findEmployeeById(int empId){
        return employeeDao.findEmployeeById(empId);
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int empId) {
        return employeeDao.deleteEmployee(empId);
    }
}
