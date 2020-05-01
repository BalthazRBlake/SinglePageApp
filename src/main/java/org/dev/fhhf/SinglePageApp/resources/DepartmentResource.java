package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spapp/dep")
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments(){
        return departmentService.findAllDepartments();
    }

    @PostMapping("/insert")
    public int addDepartment(@RequestBody Department department){
        return departmentService.insertDepartment(department);
    }

    @PutMapping("/update/{dpId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("dpId") int dpId, @RequestBody Department department){
        department.setDpId(dpId);
        int result = departmentService.updateDepartment(department);

        if(result == 0)
            throw new DataIntegrityViolationException("");

        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{dpId}")
    public int deleteDepartment(@PathVariable("dpId") int dpId){
        return departmentService.deleteDepartment(dpId);
    }
}
