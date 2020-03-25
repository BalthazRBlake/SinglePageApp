package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeDao {

    int countTotalEmployees();

    List<Employee> findAllEmployees();

    List<Employee> findAllEmployeesPaginated(int page, int size);

    List<Employee> findEmployeesNameStartsWith(String empName, int page, int size);

    Employee findEmployeeById(int empId);

    Employee insertEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int empId);
}
