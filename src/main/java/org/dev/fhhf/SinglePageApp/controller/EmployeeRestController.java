package org.dev.fhhf.SinglePageApp.controller;

import io.swagger.annotations.ApiOperation;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.dev.fhhf.SinglePageApp.service.ValidateInputDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp/emp")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ValidateInputDataService validateInputDataService;

    @GetMapping("/totalEmployees")
    public ResponseEntity<Integer> countTotalEmployees(){
        Integer count = employeeService.countTotalEmployees();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return !employees.isEmpty() ? ResponseEntity.ok(employees) : ResponseEntity.notFound().build();
    }

    @GetMapping("/paginated/{currentPage}/{perPage}")
    public ResponseEntity<List<Employee>> findPaginatedEmployees(
            @PathVariable("currentPage") int currentPage, @PathVariable("perPage") int perPage){
        List<Employee> employees = employeeService.findPaginatedEmployees(currentPage, perPage);
        return !employees.isEmpty() ? ResponseEntity.ok(employees) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Find all names beginning with given string", response = Employee.class)
    @GetMapping("/search/{empName}")
    public ResponseEntity<List<Employee>> findEmployeesNameStarsWith(@PathVariable("empName") String empName){
        List<Employee> employees = employeeService.findEmployeesNameStartsWith(empName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/id/{empId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("empId") int empId){
        validateInputDataService.validateEmployee(empId);
        return ResponseEntity.ok(employeeService.findEmployeeById(empId));
    }

    @PostMapping("/insert")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        validateInputDataService.validateDepartment(employee.getEmp_dpId().getDpId());
        employeeService.insertEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee){
        validateInputDataService.validateEmployee(empId);
        validateInputDataService.validateDepartment(employee.getEmp_dpId().getDpId());

        employee.setEmpId(empId);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{empId}")
    public int deleteEmployee(@PathVariable("empId") int empId){
        return employeeService.deleteEmployee(empId);
    }
}
