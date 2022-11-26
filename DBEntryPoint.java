import java.util.Properties;

import exceptions.SQLDBConnectionException;

import java.sql.*;

public class DBEntryPoint {
    private static String dbDriverClassName = "org.postgresql.Driver";
    private static String dbLocalUrl = "jdbc:postgresql://localhost:5432/";
    private static String dbLocalUsername = "postgres";
    private static String dbLocalPass = "data123";

    private static Connection dbInit(String sqlDbDriverClassName, String sqlDbUrl, String username, String pass) throws ClassNotFoundException, SQLDBConnectionException {
        Class.forName(sqlDbDriverClassName);
        String url = sqlDbUrl;
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", pass);
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new SQLDBConnectionException(e.getMessage());
        }
    }

    // TODO: set up logic to take in the following as main arg params: { task_num, ...tasks_query_params (if any)}
    // -- will require exception handling for checking the correct # of params for each task...
    // -- also, need to set up an easy way to then run the java project with specified args
    public static void main(String args[]) {
        Connection conn;
        try {
            conn = dbInit(dbDriverClassName, dbLocalUrl, dbLocalUsername, dbLocalPass);

            // sample query logic
            // TODO: set up logic to utilize a switch-case that will determine the correct trigger/stored procedure/function to perform (based on main arg params)
            Statement st = conn.createStatement();
            String query1 =
                    "SELECT customer_id, first_name, last_name FROM CUSTOMER WHERE customer_id=1";
            ResultSet res1 = st.executeQuery(query1);
            String id, fn, ln;
            while (res1.next()) {
                id = res1.getString("customer_id");
                fn = res1.getString("first_name");
                ln = res1.getString("last_name");
                System.out.println(id + " " + fn + " " + ln);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("The specified SQL DB Driver Class file was not found: \n" + e.getMessage());
        } catch (SQLDBConnectionException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("A SQL error occured when attempting to perform the specified query: \n" + e.getMessage());
        }
    }
}