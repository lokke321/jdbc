package ru.level.up.gdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class UsersUseMain {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите логин");
        String login = BF.readLine();
        System.out.println("Введите пароль");
        String password = BF.readLine();



    UsersUse newUser = new UsersUse(login, password);

  //      newUser.addUsers();
//        System.out.println("Пользователь " + login + " добавлен");
//
//        newUser.deleteUsers();
//         System.out.println("Пользователь " + login + " удалён");
//
//        newUser.updateUsers();
//         System.out.println("Пароль пользователя " + login + " изменен"");

//          newUser.selectUser();

           newUser.autorization();

    }



}
