package voidblue.preference.demo.Models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import voidblue.preference.demo.Service.RecommendService;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    String classname;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
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
    public OutputDao outputDao(){return  new OutputDao(jdbcTemplate());}
    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new SimpleDriverDataSource();
        try {
            ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ((SimpleDriverDataSource) dataSource).setUsername("root");
        ((SimpleDriverDataSource) dataSource).setPassword("456111");
        ((SimpleDriverDataSource) dataSource).setUrl("jdbc:mysql://220.149.42.125/preference?characterEncoding=utf-8");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public RecommendService recommendService(){
        return new RecommendService();
    }

//TODO 지워야할것
    @Bean
    public TrainingData trainingData(){
        return new TrainingData(jdbcTemplate());
    }

}
