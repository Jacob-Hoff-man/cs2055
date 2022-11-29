package services.Customer;

import java.sql.SQLException;
import java.util.List;

import models.Customer;

public interface ICustomerDao {
    public int addCustomer(Customer customer) throws SQLException;
    public int deleteCustomer(int coffeeId) throws SQLException;
    public Customer getCustomer(int customerId) throws SQLException;
    public Customer getCustomer(String customerName) throws SQLException;
    public List<Customer> getCustomers() throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
}
