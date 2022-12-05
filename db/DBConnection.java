package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private DBConnection() {
        throw new IllegalStateException("Utility Class");
    }

    /* static db connection values */
    private static String dbDriverClassName = "org.postgresql.Driver";
    private static String dbLocalUrl = "jdbc:postgresql://localhost:5432/";
    /* default value set to local psql values */
    /* TODO: set up user value input from cli args or something */
    private static String dbLocalUsername = "postgres";
    private static String dbLocalPass = "data123";

    /* singleton Connection class obj */
    private static Connection conn = null;
    
    static {
        try {
            Class.forName(dbDriverClassName);
            conn = DriverManager.getConnection(dbLocalUrl, dbLocalUsername, dbLocalPass);

        } catch (ClassNotFoundException e) {
            System.out.println("The specified SQL DB Driver Class file was not found: \n" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("A connection to the specified SQL DB could not be established." + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public void setDbUsername(String newUsername) {
        dbLocalUsername = newUsername;
    }

    public void setDbPassword(String newPass) {
        dbLocalPass = newPass;
    }
}