

import java.util.Properties;
import java.sql.*;

public class JavaDemo {
    public static void main(String args[]) throws
            SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "data123");
        Connection conn =
                DriverManager.getConnection(url, props);

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
    }
}