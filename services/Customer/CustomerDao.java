package services.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import db.DBConnection;
import models.Customer;

public class CustomerDao implements ICustomerDao {
    Connection conn = DBConnection.getConnection();

    @Override
    public int addCustomer(Customer customer) throws SQLException {
        // task 9 implementation
        CallableStatement properCase = conn.prepareCall("call add_customer( ?, ?, ?, ?, ?, ?, ? )");
        // calling SQL procedure to insert new store
        properCase.setString(1, customer.getFirstName());
        properCase.setString(2, customer.getLastName());
        properCase.setString(3, customer.getMidInitial());
        properCase.setString(4, customer.getBirthDay());
        properCase.setString(5, customer.getBirthMonth());
        properCase.setString(6, customer.getPhoneNumber());
        properCase.setString(7, customer.getPhoneType());
        properCase.execute();
        
        properCase = conn.prepareCall("{ ? = call get_customer_id( ? ) }");
        // calling SQL function to get the newly inserted store's store_number for ret
        properCase.registerOutParameter(1, Types.INTEGER);
        properCase.setString(2, customer.getPhoneNumber());
        properCase.execute();
        return properCase.getInt(1);
    }

    @Override
    public int deleteCustomer(int coffeeId) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Customer getCustomer(int customerId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public double getCustomerCurrentPoints(int customerId) throws SQLException {
        CallableStatement properCase = conn.prepareCall("{ ? = call get_customer_current_points( ? ) }");
        // calling SQL function to get the specified customer's current points for ret
        properCase.registerOutParameter(1, Types.DOUBLE);
        properCase.setInt(2, customerId);
        properCase.execute();
        return properCase.getDouble(1);
    }

    public double getCustomerTotalPoints(int customerId) throws SQLException {
        CallableStatement properCase = conn.prepareCall("{ ? = call get_customer_current_points( ? ) }");
        // calling SQL function to get the specified customer's total points for ret
        properCase.registerOutParameter(1, Types.DOUBLE);
        properCase.setInt(2, customerId);
        properCase.execute();
        return properCase.getDouble(1);
    }

    @Override
    public Customer getCustomer(String customerName) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> getCustomers() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    
}
