package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    //public String findEmployeeById(int empId){
      //  System.out.println(employeeService.findEmployeeById(1));
    public String countTotalEmployees(){
        System.out.println(employeeService.countTotalEmployees());
        return "Found";
    }
}
