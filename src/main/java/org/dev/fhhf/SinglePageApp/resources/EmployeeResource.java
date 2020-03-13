package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp/emp")
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

    @GetMapping("/search/{empName}")
    public List<Employee> findEmployeesNameStarsWith(@PathVariable("empName") String empName){
        return employeeService.findEmployeesNameStartsWith(empName, this.page, this.size);
    }

    @GetMapping("/id/{empId}")
    public Employee findEmployeeById(@PathVariable("empId") int empId){
        return employeeService.findEmployeeById(empId);
    }

    @PostMapping("/insert")
    public int addEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @PutMapping("/update/{empId}")
    public int updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee){
        employee.setEmpId(empId);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete/{empId}")
    public int deleteEmployee(@PathVariable("empId") int empId){
        return employeeService.deleteEmployee(empId);
    }
}
