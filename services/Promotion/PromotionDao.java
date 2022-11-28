package services.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import models.Promotion;

public class PromotionDao implements IPromotionDao {
    static Connection conn = DBConnection.getConnection();
    
    @Override
    public int addPromotion(Promotion promotion) throws SQLException {
        String query = "INSERT INTO PROMOTION (promo_name, start_date, end_date) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, promotion.getPromoName());
        ps.setDate(2, promotion.getStartDate());
        ps.setDate(3, promotion.getEndDate());

        ps.executeUpdate();
        // return the new promo's generated id from db - not efficient
        return getPromotion(promotion.getPromoName()).getPromoNumber();
    }

    public int addIncludes(int promoNumber, int coffeeId) throws SQLException {
        String query = "INSERT INTO INCLUDES (promo_number, coffee_id) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, promoNumber);
        ps.setInt(2, coffeeId);
        
        return ps.executeUpdate();
    }

    public int addPromotionWithIncludedCoffee(Promotion promotion, int coffeeId) throws SQLException {
        try {
            conn.setAutoCommit(false);
            // insert promo entity into table
            int promoNumber = addPromotion(promotion);
            // insert includes relationship entity into table
            addIncludes(promoNumber, coffeeId);
            // commit transaction
            conn.commit();
            conn.setAutoCommit(true);
            // return the new promo's generated id from db - not efficient
            return getPromotion(promotion.getPromoName()).getPromoNumber();

        } catch (SQLException e1) {
            // rollback any changes that occured before transaction failure
            try {
                conn.rollback();
                throw new SQLException(e1);
            } catch (SQLException e2) {
                System.out.println("An error occured while performing rollback:");
                System.out.println(e2.getMessage());
                System.out.println(e2.getErrorCode());
                System.out.println(e2.getSQLState());
                System.out.println(e2.getStackTrace());
                throw new SQLException(e1);
            }
        } 
    }

    @Override
    public int deletePromotion(int promoNumber) throws SQLException {
        String query = "DELETE FROM PROMOTION WHERE promo_number = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, promoNumber);
        
        return ps.executeUpdate();
    }

    @Override
    public Promotion getPromotion(int promoNumber) throws SQLException {
        String query = "SELECT * FROM PROMOTION WHERE promo_number = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, promoNumber);
        Promotion promotion = new Promotion();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            promotion.setPromoNumber(rs.getInt("promo_number"));
            promotion.setPromoName(rs.getString("promo_name"));
            promotion.setStartDate(rs.getDate("start_date"));
            promotion.setEndDate(rs.getDate("end_date"));
        }

        if (flag) {
            return promotion; 
        } else {
            return null;
        }
    }

    @Override
    public Promotion getPromotion(String promoName) throws SQLException {
        String query = "SELECT * FROM PROMOTION WHERE promo_name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, promoName);
        Promotion promotion = new Promotion();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            promotion.setPromoNumber(rs.getInt("promo_number"));
            promotion.setPromoName(rs.getString("promo_name"));
            promotion.setStartDate(rs.getDate("start_date"));
            promotion.setEndDate(rs.getDate("end_date"));
        }

        if (flag) {
            return promotion; 
        } else {
            return null;
        }
    }

    @Override
    public List<Promotion> getPromotions() throws SQLException {
        String query = "SELECT * FROM PROMOTION";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Promotion> promotions = new ArrayList<>();

        while (rs.next()) {
            Promotion promotion = new Promotion();
            promotion.setPromoNumber(rs.getInt("promo_number"));
            promotion.setPromoName(rs.getString("promo_name"));
            promotion.setStartDate(rs.getDate("start_date"));
            promotion.setEndDate(rs.getDate("end_date"));
            promotions.add(promotion);
        }

        return promotions;
    }

    @Override
    public void updatePromotion(Promotion promotion) throws SQLException {
        String query = "UPDATE PROMOTION SET promo_name=?, start_date=?, end_date=? WHERE promo_number=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, promotion.getPromoName());
        ps.setDate(2, promotion.getStartDate());
        ps.setDate(3, promotion.getEndDate());
        ps.executeUpdate();
    }
}
