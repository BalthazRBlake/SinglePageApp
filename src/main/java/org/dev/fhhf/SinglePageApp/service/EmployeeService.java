package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Employee;

public interface EmployeeService {

    int countTotalEmployees();

    Employee findEmployeeById(int empId);
}
