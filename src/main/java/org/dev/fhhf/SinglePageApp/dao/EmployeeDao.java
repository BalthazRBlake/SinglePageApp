package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeDao {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    List<Employee> findPaginatedEmployees(int currentPage, int perPage);

    List<Employee> findEmployeesNameStartsWith(String empName);

    Employee findEmployeeById(int empId);

    Employee insertEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int empId);
}
