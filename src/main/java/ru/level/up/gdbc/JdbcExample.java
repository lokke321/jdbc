package ru.level.up.gdbc;

import java.sql.*;

public class JdbcExample {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        // URL:  jdbc:<vendor_name>://<host>:<port>/<DB_name>[options]
        //класс представляет собой физическое соединение с СУБД

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5431/coto-chat",
                "postgres",
                "root"

        );

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users");


        long maxID = 0;
        long minID = 0;

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString ("password");
            System.out.println(id + " " + login + " " + password );

            maxID = Math.max(id, maxID);
            minID = Math.min(id, minID);
        }

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(login, password) values (?,  ?)");

        preparedStatement.setString(1, "test_login_" + maxID);
        preparedStatement.setString(2, "test_password");

        int count = preparedStatement.executeUpdate();
        System.out.println("Affected rows: " + count);

        PreparedStatement deleteStatment = connection.prepareStatement(
                "delete from users where id = ?"
        );
        deleteStatment.setLong(1, maxID);
        int deleted = deleteStatment.executeUpdate();
        System.out.println("Deleted: " + deleted);

    }



}
