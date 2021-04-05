package com.example.spjdbc.controller;

import com.example.spjdbc.dao.EmployeeDao;
import com.example.spjdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao empolyeeDao;

//    public EmployeeController(EmpolyeeDao empolyeeDao) {
//        this.empolyeeDao = empolyeeDao;
//    }

    @GetMapping("/getemployee")
    public List<Employee> getEmp(){
    return empolyeeDao.getEmployee();
    }

    @PostMapping("/saveemployee")
    public String saveEmployee(@RequestBody Employee employee){
        return empolyeeDao.insertData(employee);
    }
    @GetMapping("getemp/{id}")
    public Employee getEp(@PathVariable Integer id){
        return empolyeeDao.getEmp(id);
    }
    @PutMapping("/updateemployee")
    public String updateEmp(@RequestBody Employee employee){
     return empolyeeDao.updateEmployee(employee);
    }
    @GetMapping("/deleteemp/{id}")
    public String empDel(@PathVariable Integer id){
        return empolyeeDao.deleteEmp(id);
    }
    @GetMapping("/combine")
    public List<Map<String,Object>>getCombine(){
    return empolyeeDao.combineData();
    }


}
