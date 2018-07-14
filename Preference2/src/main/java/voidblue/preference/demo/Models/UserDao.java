package voidblue.preference.demo.Models;

import lombok.Cleanup;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.HashMap;

@Configuration
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public User getUser(String userId){
        String sql = "select * from USERS where ID =?";
        Object[] params = {userId};
        return jdbcTemplate.queryForObject(sql, params, ((rs, rowNum) -> {
            User user = new User();
            user.setSex(rs.getString("SEX"));
            user.setBirth(rs.getString("BIRTH"));
            user.setEducation(rs.getString("EDUCATION"));
            user.setJob(rs.getString("JOB"));
            user.setRegion(rs.getString("REGION"));
            return user;
        }));

    }
}
