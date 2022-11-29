package services.Coffee;

import java.sql.SQLException;
import java.util.List;

import models.Coffee;

public interface ICoffeeDao {
    public int addCoffee(Coffee coffee) throws SQLException;
    public int deleteCoffee(int coffeeId) throws SQLException;
    public Coffee getCoffee(int coffeeId) throws SQLException;
    public Coffee getCoffee(String coffeeName) throws SQLException;
    public List<Coffee> getCoffees() throws SQLException;
    public void updateCoffee(Coffee store) throws SQLException;
}
