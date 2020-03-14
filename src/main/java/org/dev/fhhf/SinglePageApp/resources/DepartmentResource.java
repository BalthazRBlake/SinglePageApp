package org.dev.fhhf.SinglePageApp.resources;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
