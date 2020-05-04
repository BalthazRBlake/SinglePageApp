package org.dev.fhhf.SinglePageApp.controller;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.dev.fhhf.SinglePageApp.service.ValidateInputDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp/dep")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ValidateInputDataService validateInputDataService;

    @GetMapping("/all")
    public List<Department> getAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @PostMapping("/insert")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.insertDepartment(department));
    }

    @PutMapping("/update/{dpId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("dpId") int dpId, @RequestBody Department department){
        validateInputDataService.validateDepartment(dpId);

        department.setDpId(dpId);
        departmentService.updateDepartment(department);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{dpId}")
    public int deleteDepartment(@PathVariable("dpId") int dpId){
        return departmentService.deleteDepartment(dpId);
    }
}
