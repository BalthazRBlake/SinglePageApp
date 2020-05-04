package org.dev.fhhf.SinglePageApp.controller;

import io.swagger.annotations.ApiOperation;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp/emp")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/pages")
    public ResponseEntity<Integer> countTotalEmployees(){
        Integer count = employeeService.countTotalEmployees();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return !employees.isEmpty() ? ResponseEntity.ok(employees) :
                                      ResponseEntity.notFound().build();
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<List<Employee>> findAllEmployeesPaginated(@PathVariable("page") int page,
                                                    @PathVariable("size") int size){
        List<Employee> employees = employeeService.findAllEmployeesPaginated(page, size);
        return !employees.isEmpty() ? ResponseEntity.ok(employees) :
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Find all names beginning by given string",
                    response = Employee.class)
    @GetMapping("/search/{empName}")
    public ResponseEntity<List<Employee>> findEmployeesNameStarsWith(@PathVariable("empName") String empName){
        List<Employee> employees = employeeService.findEmployeesNameStartsWith(empName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/id/{empId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("empId") int empId){
        Employee employee = employeeService.findEmployeeById(empId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/insert")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        employeeService.insertEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee){
        employee.setEmpId(empId);
        int result = employeeService.updateEmployee(employee);

        if(result == 0)
            throw new EmptyResultDataAccessException(0);

        return ResponseEntity.ok(employeeService.findEmployeeById(empId));
    }

    @DeleteMapping("/delete/{empId}")
    public int deleteEmployee(@PathVariable("empId") int empId){
        return employeeService.deleteEmployee(empId);
    }
}
