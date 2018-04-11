import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;

public class DBtest {
    Connection connection;

    @Before
    public void Connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://220.149.42.125:3306/preference?characterEncoding=utf-8", "root", "456111");
    }

    @Test
    public void getData() throws SQLException {
        String userId = "test1111";
        HashMap hashMap = new HashMap();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where ID =?");
        preparedStatement.setString(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString("ID"));
    }
}
