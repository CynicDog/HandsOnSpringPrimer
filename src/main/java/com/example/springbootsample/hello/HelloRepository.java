package com.example.springbootsample.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
// @Repository: Refers to any repository Represents DB operations.
//             A repository is a class that performs database-related processing.
public class HelloRepository {

    @Autowired
    // @Autowired: An annotation for Dependency Injection.
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findById(String id) {
        String query = "SELECT *" + " FROM employee" + " WHERE id=?";

        Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);

        return employee;
    }
}
