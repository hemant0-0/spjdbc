package com.example.spjdbc.dao;

import com.example.spjdbc.model.Department;
import com.example.spjdbc.model.Employee;
import com.example.spjdbc.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class DepartmentDao implements DepartmentRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addDepartmentAndEmployee(Department department) {
        String sql="insert into department(name) values (?) ";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,department.getName());
                return ps;
            }
        }, holder);

        int newUserId = holder.getKey().intValue();
        for (Employee e:department.getEmployeeList()) {
            String sql2 = "insert into employee(name,place,dept_id) values (?,?,?)";
            jdbcTemplate.update(sql2, new Object[]{e.getName(),e.getPlace(),newUserId});
        }
            return "department Employee ADDed";
    }
}
