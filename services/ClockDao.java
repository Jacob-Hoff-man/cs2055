package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import db.DBConnection;

public class ClockDao {
    static Connection conn = DBConnection.getConnection();

    public Date updateClock(Date date) throws SQLException {
        // task 9 implementation
        CallableStatement properCase = conn.prepareCall("call update_clock_date( ? )");
        // calling SQL procedure to update clock date
        properCase.setDate(1, date);
        properCase.execute();
        
        properCase = conn.prepareCall("{ ? = call get_p_clock_date() }");
        // calling SQL function to get the updated clock date
        properCase.registerOutParameter(1, Types.DATE);
        properCase.execute();
        return properCase.getDate(1);
    }
}
