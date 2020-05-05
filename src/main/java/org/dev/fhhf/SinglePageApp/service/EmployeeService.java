package org.dev.fhhf.SinglePageApp.service;

import net.bytebuddy.matcher.ElementMatcher;
import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeService {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    List<Employee> findPaginatedEmployees(int page, int size);

    List<Employee> findEmployeesNameStartsWith(String empName);

    Employee findEmployeeById(int empId);

    Employee insertEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int empId);
}
