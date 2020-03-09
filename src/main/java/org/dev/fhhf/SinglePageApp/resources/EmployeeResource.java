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

@RestController("/spapp")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/count")
    public String countTotalEmployees() {
        return String.valueOf( employeeService.countTotalEmployees() );
    }

    @GetMapping("/all")
    public List<Employee> findAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/paginated/{page}/{size}")
    public List<Employee> findAllEmployeesPaginated(@PathVariable("page") int page,
                                                    @PathVariable("size") int size){
        return employeeService.findAllEmployeesPaginated(page,size);
    }

    @GetMapping("/search/{empName}/{page}/{size}")
    public List<Employee> findEmployeesNameStarsWith(@PathVariable("empName") String empName,
                                                     @PathVariable("page") int page,
                                                     @PathVariable("size") int size){
        return employeeService.findEmployeesNameStartsWith(empName, page, size);
    }

    @GetMapping("/id/{empId}")
    public Employee findEmployeeById(@PathVariable("empId") int empId){
        return employeeService.findEmployeeById(empId);
    }
}
