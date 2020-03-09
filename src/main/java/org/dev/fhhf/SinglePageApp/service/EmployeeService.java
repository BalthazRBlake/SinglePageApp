package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeService {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    List<Employee> findAllEmployeesPaginated(int page, int size);

    List<Employee> findEmployeesNameStartsWith(String empName, int page, int size);

    Employee findEmployeeById(int empId);
}
