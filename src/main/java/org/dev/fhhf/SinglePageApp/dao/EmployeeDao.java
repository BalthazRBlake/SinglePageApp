package org.dev.fhhf.SinglePageApp.dao;

import org.dev.fhhf.SinglePageApp.model.Employee;

import java.util.List;

public interface EmployeeDao {

    int countTotalEmployees();

    List<EmployeeDao> findAllEmployees();

    Employee findEmployeeById(int empId);
}
