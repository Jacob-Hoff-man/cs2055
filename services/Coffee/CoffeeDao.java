package services.Coffee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import models.Coffee;

public class CoffeeDao implements ICoffeeDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public int addCoffee(Coffee coffee) throws SQLException {
        // String query = "INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ps.setString(1, coffee.getCoffeeName());
        // ps.setString(2, coffee.getDescription());
        // ps.setString(3, coffee.getCountry());
        // ps.setInt(4, coffee.getIntensity());
        // ps.setFloat(5, coffee.getPrice());
        // ps.setFloat(6, coffee.getRedeemPoints());
        // ps.setFloat(7, coffee.getRewardPoints());

        // return ps.executeUpdate();
        return 1;
    }

    @Override
    public int deleteCoffee(int coffeeId) throws SQLException {
        String query = "DELETE FROM COFFEE WHERE coffee_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, coffeeId);
        
        return ps.executeUpdate();
    }

    @Override
    public Coffee getCoffee(int coffeeId) throws SQLException {
        String query = "SELECT * FROM COFFEE WHERE coffee_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, coffeeId);
        Coffee coffee = new Coffee();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            coffee.setCoffeeId(rs.getInt("coffee_id"));
            coffee.setCoffeeName(rs.getString("coffee_name"));
            coffee.setDescription(rs.getString("description"));
            coffee.setCountry(rs.getString("country"));
            coffee.setIntensity(rs.getInt("intensity"));
            coffee.setPrice(rs.getFloat("price"));
            coffee.setRedeemPoints(rs.getFloat("redeem_points"));
            coffee.setRewardPoints(rs.getFloat("reward_points"));
        }

        if (flag) {
            return coffee; 
        } else {
            return null;
        }
    }

    @Override
    public Coffee getCoffee(String coffeeName) throws SQLException {
        String query = "SELECT * FROM COFFEE WHERE coffee_name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, coffeeName);
        Coffee coffee = new Coffee();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            coffee.setCoffeeId(rs.getInt("coffee_id"));
            coffee.setCoffeeName(rs.getString("coffee_name"));
            coffee.setDescription(rs.getString("description"));
            coffee.setCountry(rs.getString("country"));
            coffee.setIntensity(rs.getInt("intensity"));
            coffee.setPrice(rs.getFloat("price"));
            coffee.setRedeemPoints(rs.getFloat("redeem_points"));
            coffee.setRewardPoints(rs.getFloat("reward_points"));
        }

        if (flag) {
            return coffee; 
        } else {
            return null;
        }
    }

    @Override
    public List<Coffee> getCoffees() throws SQLException {
        String query = "SELECT * FROM COFFEE";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Coffee> coffees = new ArrayList<>();

        while (rs.next()) {
            Coffee coffee = new Coffee();
            coffee.setCoffeeId(rs.getInt("coffee_id"));
            coffee.setCoffeeName(rs.getString("coffee_name"));
            coffee.setDescription(rs.getString("description"));
            coffee.setCountry(rs.getString("country"));
            coffee.setIntensity(rs.getInt("intensity"));
            coffee.setPrice(rs.getFloat("price"));
            coffee.setRedeemPoints(rs.getFloat("redeem_points"));
            coffee.setRewardPoints(rs.getFloat("reward_points"));
            coffees.add(coffee);
        }

        return coffees;
    }

    @Override
    public void updateCoffee(Coffee coffee) throws SQLException {
        String query = "UPDATE COFFEE SET coffee_name=?, description=?, country=?, intensity=?, price=?, redeem_points=?, reward_points=? WHERE coffee_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, coffee.getCoffeeName());
        ps.setString(2, coffee.getDescription());
        ps.setString(3, coffee.getCountry());
        ps.setInt(4, coffee.getIntensity());
        ps.setFloat(5, coffee.getPrice());
        ps.setFloat(5, coffee.getRedeemPoints());
        ps.setFloat(5, coffee.getRewardPoints());
        ps.setInt(5, coffee.getCoffeeId());
        ps.executeUpdate();
    }
    
}
