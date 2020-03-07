package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String findEmployeeById(){
        Employee employee = employeeService.findEmployeeById(1);
        System.out.println(employee);
    //public String countTotalEmployees(){
        //System.out.println(employeeService.countTotalEmployees());
        return employee.toString();
    }
}
