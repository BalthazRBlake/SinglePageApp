package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeService {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    List<Employee> findEmployeesNameStartsWith(String empName);

    Employee findEmployeeById(int empId);
}
