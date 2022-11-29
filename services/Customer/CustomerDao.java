package services.Customer;

import java.sql.SQLException;
import java.util.List;

import models.Customer;

public class CustomerDao implements ICustomerDao {

    @Override
    public int addCustomer(Customer customer) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
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
