package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;
    //@Autowired
    //private DepartmentService departmentService;

    @GetMapping("/")
    public String countTotalEmployees() {
        return "# Employees: " + employeeService.countTotalEmployees();
    }

    @GetMapping("/all")
    public List<Employee> findAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/search/{empName}")
    public List<Employee> findEmployeesNameStarsWith(@PathVariable("empName") String empName){
        return employeeService.findEmployeesNameStartsWith(empName);
    }

    @GetMapping("/id/{empId}")
    public Employee findEmployeeById(@PathVariable("empId") int empId){
        return employeeService.findEmployeeById(empId);
    }
}
