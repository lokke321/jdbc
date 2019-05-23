package ru.level.up.gdbc;

import java.sql.SQLException;

public class CheckedTables {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        UsersUse check = new UsersUse("","");
        check.CheckTables();

    }
}
