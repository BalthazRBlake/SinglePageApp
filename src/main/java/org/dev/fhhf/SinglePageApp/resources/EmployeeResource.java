package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    private int page = 1, size = 5;

    @GetMapping("/pages")
    public int countTotalEmployees(){
        return employeeService.countTotalEmployees();
    }

    @GetMapping("/all")
    public List<Employee> findAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/paginated/{page}/{size}")
    public List<Employee> findAllEmployeesPaginated(@PathVariable("page") int page,
                                                    @PathVariable("size") int size){
        this.page = page;
        this.size = size;
        return employeeService.findAllEmployeesPaginated(this.page,this.size);
    }

    @GetMapping("/search/{empName}/{page}/{size}")
    public List<Employee> findEmployeesNameStarsWith(@PathVariable("empName") String empName,
                                                     @PathVariable("page") int page,
                                                     @PathVariable("size") int size){
        this.page = page;
        this.size = size;
        return employeeService.findEmployeesNameStartsWith(empName, this.page, this.size);
    }

    @GetMapping("/id/{empId}")
    public Employee findEmployeeById(@PathVariable("empId") int empId){
        return employeeService.findEmployeeById(empId);
    }
}
