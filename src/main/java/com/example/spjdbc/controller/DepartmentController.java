package com.example.spjdbc.controller;

import com.example.spjdbc.model.Department;
import com.example.spjdbc.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentRepo departmentRepo;
        @PostMapping("/saveDeptEmp")
        public String saveDeptEmp(@RequestBody Department department){
                return departmentRepo.addDepartmentAndEmployee(department);
            }
}
