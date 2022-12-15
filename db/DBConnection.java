package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /* current working directory path name for the file location */
    private static String cwd = System.getProperty("user.dir") + "/db/";

    /* static db connection values */
    private static String dbDriverClassName = "org.postgresql.Driver";
    private static String dbLocalUrl = "jdbc:postgresql://localhost:5432/";
    /* default value set to local psql values */
    /* TODO: set up user value input from cli args or something */
    private static String dbLocalUsername = "postgres";
    private static String dbLocalPass = "data123";

    /* singleton Connection class obj */
    private static Connection conn = null;
    
    private DBConnection() {
        throw new IllegalStateException("Utility Class");
    }

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

    public static void restartDatabaseState() {
        try {
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.runScript(new BufferedReader(new FileReader(cwd + "generate_tables.sql")));
            runner.runScript(new BufferedReader(new FileReader(cwd + "sample_data.sql")));

        } catch (FileNotFoundException e) {
            System.out.println("The specified SQL file was not found: \n" + e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("The specified SQL file could not be read: \n" + e.getMessage());
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void emptyDatabaseState() {
        try {
            ScriptRunner runner = new ScriptRunner(conn, false, true);
            runner.runScript(new BufferedReader(new FileReader(cwd + "generate_tables.sql")));

        } catch (FileNotFoundException e) {
            System.out.println("The specified SQL file was not found: \n" + e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("The specified SQL file could not be read: \n" + e.getMessage());
            e.printStackTrace();

        } catch (SQLException e) {
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