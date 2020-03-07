package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeService {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    Employee findEmployeeById(int empId);
}
