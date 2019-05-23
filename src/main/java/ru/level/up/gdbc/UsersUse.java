package ru.level.up.gdbc;
import java.sql.*;

public class UsersUse {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private  String login;
    private String password;

   public UsersUse(String login, String password) throws SQLException {
       this.login = login;
       this.password = password;
   }


    public String getLogin() {return login;}

    public String getPassword() {return password;}


    Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5431/coto-chat",
            "postgres",
            "root"

    );
    Statement statement = connection.createStatement();

    ResultSet resultSet = statement.executeQuery("select * from users");



    public void CheckTables() throws SQLException {


        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            System.out.println(id + " " + login + " " + password );


        }

    }


    public void updateUsers() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE login = ?");

        preparedStatement.setString(2, login);
        preparedStatement.setString(1, password);

        int count = preparedStatement.executeUpdate();
        System.out.println("Affected rows: " + count);
    }




    public void addUsers() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(login, password) values (?,  ?)");

        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);

        int count = preparedStatement.executeUpdate();
        System.out.println("Affected rows: " + count);

    }



   public void deleteUsers() throws SQLException {

       PreparedStatement deleteStatment = connection.prepareStatement(
               "delete from users where login = ?"
       );
       deleteStatment.setString(1, login);

       int deleted = deleteStatment.executeUpdate();
       System.out.println("Deleted: " + deleted);
   }


   public void selectUser() throws SQLException {

        String sql = "select * from users where login = '" + login +"'" ;

      ResultSet rs = statement.executeQuery(sql);


      while (rs.next()) {
          long id = rs.getLong("id");
          String login = rs.getString("login");
          String password = rs.getString("password");
          System.out.println(id + " " + login + " " + password );

      }
   }

   public void autorization () throws SQLException {

       String sql = "select * from users where login = '" + login + "'" + " and password = '" + password + "'";

       ResultSet rs = statement.executeQuery(sql);


       if (rs.next()) {

           System.out.println("Авторизация выполнена");

       }else {

       System.out.println("Не правильный логин или пароль");
       }
   }
}
