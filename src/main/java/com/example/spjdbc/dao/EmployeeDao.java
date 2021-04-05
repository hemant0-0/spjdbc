package com.example.spjdbc.dao;

import com.example.spjdbc.model.Employee;
import com.example.spjdbc.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
//@Component
public class EmployeeDao implements MyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Employee> getEmployee() {
        String sql="select * from employee";
        List<Employee> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    @Override
    public String insertData(Employee employee) {
        String sql="insert into employee values(?,?,?)";
      //  jdbcTemplate.update(sql,new Object[]{employee.getId(),employee.getName(),employee.getPlace()});
        jdbcTemplate.update(sql,employee.getId(),employee.getName(),employee.getPlace());
        return "employee Added";
    }

    @Override
    public Employee getEmp(Integer id) {
        String sql="select * from employee where id=?";
        Employee emp=jdbcTemplate.queryForObject(sql,new Object[]{id},
                                            new int[]{Types.INTEGER},
                                            new BeanPropertyRowMapper<>(Employee.class));
        return emp;
    }

    @Override
    public String updateEmployee(Employee employee) {
        String sql="update employee set name=?,place=? where id=?";
        jdbcTemplate.update(sql,new Object[]{employee.getName(),employee.getPlace(),employee.getId()});
        return "employee updated";
    }

    @Override
    public String deleteEmp(Integer id) {
        String sql="delete from employee where id=?";
        //jdbcTemplate.update(sql,new Object[]{id},new int[]{Types.INTEGER});
        jdbcTemplate.update(sql,id);
        return "Employee Deleted";
    }

    @Override
    public List<Map<String, Object>> combineData() {
        String sql="select a.id,a.name,a.place,b.name as deptname from employee a,department b where a.dept_id=b.id";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }

}
