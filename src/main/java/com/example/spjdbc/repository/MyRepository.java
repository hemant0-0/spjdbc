package com.example.spjdbc.repository;

import com.example.spjdbc.model.Employee;

import java.util.List;
import java.util.Map;

public interface MyRepository  {

    List<Employee> getEmployee();
    public String insertData(Employee employee);
     Employee getEmp(Integer id);
     String updateEmployee(Employee employee);
     String deleteEmp(Integer id);
     List<Map<String,Object>> combineData();

}
