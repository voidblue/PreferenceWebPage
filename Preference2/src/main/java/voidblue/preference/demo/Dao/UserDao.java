package voidblue.preference.demo.Dao;

import lombok.Cleanup;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.HashMap;

@Configuration
public class UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://220.149.42.125:3306/preference?characterEncoding=utf-8","root","456111");
        return connection;
    }
    public User getUser(String userId) throws SQLException, ClassNotFoundException {
        HashMap hashMap = new HashMap();
        @Cleanup
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from USERS where ID =?");
        preparedStatement.setString(1, userId);
        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if(resultSet.next()) {
            user = new User();
            user.setSex(resultSet.getString("SEX"));
            user.setBirth(resultSet.getString("BIRTH"));
            user.setEducation(resultSet.getString("EDUCATION"));
            user.setJob(resultSet.getString("JOB"));
        }
        return user;

    }



}
