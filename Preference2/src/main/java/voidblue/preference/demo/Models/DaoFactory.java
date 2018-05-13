package voidblue.preference.demo.Models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname")
    String classname;
    @Value("${db.username}")
    String username;
    @Value("${db.password")
    String password;
    @Value("${db.url}")
    String url;

    @Bean
    public InputDao inputDao(){
        return new InputDao(jdbcTemplate());
    }

    @Bean
    public UserDao userDao(){
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new SimpleDriverDataSource();
        try {
            ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>) Class.forName("classname"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ((SimpleDriverDataSource) dataSource).setUsername(username);
        ((SimpleDriverDataSource) dataSource).setPassword(password);
        ((SimpleDriverDataSource) dataSource).setUrl(url);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
