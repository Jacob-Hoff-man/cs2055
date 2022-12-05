package services.Coffee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import models.Coffee;

public class CoffeeDao implements ICoffeeDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public int addCoffee(Coffee coffee) throws SQLException {
        // // jdbc implementation
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

        // task 2 implementation
        CallableStatement properCase = conn.prepareCall("call add_coffee( ?, ?, ?, ?, ?, ?, ? )");
        // calling SQL procedure to insert new coffee
        properCase.setString(1, coffee.getCoffeeName());
        properCase.setString(2, coffee.getDescription());
        properCase.setString(3, coffee.getCountry());
        properCase.setInt(4, coffee.getIntensity());
        properCase.setFloat(5, coffee.getPrice());
        properCase.setFloat(6, coffee.getRedeemPoints());
        properCase.setFloat(7, coffee.getRewardPoints());
        properCase.execute();
        
        properCase = conn.prepareCall("{ ? = call get_coffee_id( ? ) }");
        // calling SQL function to get the newly inserted coffee's coffee_id for ret
        properCase.registerOutParameter(1, Types.INTEGER);
        properCase.setString(2, coffee.getCoffeeName());
        properCase.execute();
        return properCase.getInt(1);
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
        // jdbc implementation
        // String query = "SELECT * FROM COFFEE";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ResultSet rs = ps.executeQuery();
        // List<Coffee> coffees = new ArrayList<>();

        // while (rs.next()) {
        //     Coffee coffee = new Coffee();
        //     coffee.setCoffeeId(rs.getInt("coffee_id"));
        //     coffee.setCoffeeName(rs.getString("coffee_name"));
        //     coffee.setDescription(rs.getString("description"));
        //     coffee.setCountry(rs.getString("country"));
        //     coffee.setIntensity(rs.getInt("intensity"));
        //     coffee.setPrice(rs.getFloat("price"));
        //     coffee.setRedeemPoints(rs.getFloat("redeem_points"));
        //     coffee.setRewardPoints(rs.getFloat("reward_points"));
        //     coffees.add(coffee);
        // }

        // return coffees;

        // task 13 implementation
        CallableStatement properCase = conn.prepareCall("{ ? = call get_coffees() }");
        // calling SQL function to get the coffees in db for ret
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        List<Coffee> coffees = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
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
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return coffees;
    }

    public List<Coffee> getCoffeesByIntensityAndTwoKeywords(int intensity, String kw1, String kw2) throws SQLException {
        // task 14 implementation
        CallableStatement properCase = conn.prepareCall("{ ? = call get_coffees_by_intensity_and_two_keywords( ?, ?, ?) }");
        // calling SQL function to get the coffees in db by matching intensity, kw1, kw2 for ret
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setInt(2, intensity);
        properCase.setString(3, kw1);
        properCase.setString(4, kw2);
        List<Coffee> coffees = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
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
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
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
