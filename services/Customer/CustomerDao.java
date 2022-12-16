package services.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
        String query = "SELECT * FROM CUSTOMER WHERE customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, customerId);
        Customer customer = new Customer();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setMidInitial(rs.getString("mid_initial"));
            customer.setBirthMonth(rs.getString("birth_month"));
            customer.setBirthDay(rs.getString("birth_day"));
            customer.setPhoneNumber(rs.getString("phone_number"));
            customer.setPhoneType(rs.getString("phone_type"));
            customer.setLoyaltyLevel(rs.getString("loyalty_level"));
            customer.setCurrentPoints(rs.getFloat("current_points"));
            customer.setTotalPoints(rs.getFloat("total_points"));
        
        }

        if (flag) {
            return customer; 
        } else {
            return null;
        }
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
        CallableStatement properCase = conn.prepareCall("{ ? = call get_customer_total_points( ? ) }");
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
    
    public List<Customer> getCustomersRankedByPoints() throws SQLException {
        // task 11 implementation
        // calling SQL function to get query table of customers grouped by loyalty_level, ordered by total_points asc for ret
        CallableStatement properCase = conn.prepareCall("{ ? = call get_customers_ranked_by_current_points_grouped_by_loyalty_level() }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        List<Customer> customers = new ArrayList<>();
        try {
            conn.setAutoCommit(false);  
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setMidInitial(rs.getString("mid_initial"));
                customer.setBirthMonth(rs.getString("birth_month"));
                customer.setBirthDay(rs.getString("birth_day"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setPhoneType(rs.getString("phone_type"));
                customer.setLoyaltyLevel(rs.getString("loyalty_level"));
                customer.setCurrentPoints(rs.getFloat("current_points"));
                customer.setTotalPoints(rs.getFloat("total_points"));
                customers.add(customer);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return customers;
    }

    public List<Integer> getTopKCustomersByHighestPurchasedSumInXMonths(int k, int months) throws SQLException {
        CallableStatement properCase = conn.prepareCall("{ ? = call get_top_k_customers_by_highest_purchased_sum_in_x_months( ?, ? ) }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setInt(2, k);
        properCase.setInt(3, months);
        List<Integer> customerIds = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
                properCase.execute();
                ResultSet rs = (ResultSet)properCase.getObject(1);
                while (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    customerIds.add(customerId);
                }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return customerIds;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    
}
