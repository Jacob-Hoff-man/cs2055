package services.Sale;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import db.DBConnection;
import models.Sale;
import models.Record;

public class SaleDao implements ISaleDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public int addSale(Sale sale) throws SQLException {
        // task 12 implementation
        try {
            //begin transaction
            conn.setAutoCommit(false);

            int recordsCount = sale.getRecords().size();
            int purchaseId;
            float purchasedPortionTotal = 0;
            float coffeePriceTotal = 0;
            // add base sale
            CallableStatement properCase = conn.prepareCall("{ ? = call add_new_sale( ?, ? ) }");
            properCase.registerOutParameter(1, Types.INTEGER);
            properCase.setInt(2, sale.getCustomerId());
            properCase.setTimestamp(3, sale.getPurchasedTime());
            properCase.execute();
            purchaseId = properCase.getInt(1);
            
            double currentCustomerPoints;
            // get current_points of customer, save to current_customer_points
            properCase = conn.prepareCall("{ ? = call get_customer_current_points( ? ) }");
            properCase.registerOutParameter(1, Types.DOUBLE);
            properCase.setInt(2, sale.getCustomerId());
            properCase.execute();
            currentCustomerPoints = properCase.getDouble(1);

            
            // loop through records
            for(int i = 0; i < recordsCount; i++) {
                Record currentRec = sale.getRecords().get(i);

                // - purchased_portion_total summation
                purchasedPortionTotal += currentRec.getPurchasedPortion();
                // - if current inp_redeemed_function is > current_customer_points, throw SQLException
                if (currentRec.getRedeemedPortion() > currentCustomerPoints) {
                    throw new SQLException("Current Customer has an insufficient reward points balance for the attempted Sale.");
                }
                
                double currentRedeemPoints, currentPrice;
                // - get redeem_points, price for the current record's coffee_id, save into current_redeem_points and current_price
                properCase = conn.prepareCall("{ ? = call get_coffee_redeem_points( ? ) }");
                properCase.registerOutParameter(1, Types.DOUBLE);
                properCase.setInt(2, currentRec.getCoffeeId());
                properCase.execute();
                currentRedeemPoints = properCase.getDouble(1);

                properCase = conn.prepareCall("{ ? = call get_coffee_price( ? ) }");
                properCase.registerOutParameter(1, Types.DOUBLE);
                properCase.setInt(2, currentRec.getCoffeeId());
                properCase.execute();
                currentPrice = properCase.getDouble(1);

                // - calculate current_discount factor (current inp_redeemed_portion / current_redeem_points)
                double currentDiscountFactor = currentRec.getRedeemedPortion() / currentRedeemPoints;

                if (currentDiscountFactor > 1) {
                    currentDiscountFactor = 1;
                    currentCustomerPoints -= currentRedeemPoints;
                } else {
                    currentCustomerPoints -= currentRec.getRedeemedPortion();
                }

                // - calculate coffee_price_total (= coffee_price_total + current_price * (1 - current_discount_factor))
                coffeePriceTotal += currentPrice * (1 - currentDiscountFactor);

                // - add_record_or_increment_records_quantity
                properCase = conn.prepareCall("call add_records_or_increment_records_quantity( ?, ?, ?, ?, ? )");
                properCase.setInt(1, purchaseId);
                properCase.setInt(2, currentRec.getStoreNumber());
                properCase.setInt(3, currentRec.getCoffeeId());
                properCase.setFloat(4, currentRec.getPurchasedPortion());
                properCase.setFloat(5, currentRec.getRedeemedPortion());
                properCase.execute();
            }

            // update the customer with the new current_points
            properCase = conn.prepareCall("{ ? = call update_customer_current_points( ?, ? ) }");
            properCase.registerOutParameter(1, Types.INTEGER);
            properCase.setInt(2, sale.getCustomerId());
            properCase.setFloat(3, (float)currentCustomerPoints);
            properCase.execute();
            
            // update the sale with the calculated balance
            properCase = conn.prepareCall("{ ? = call update_sale_balance( ?, ? ) }");
            properCase.registerOutParameter(1, Types.INTEGER);
            properCase.setInt(2, purchaseId);
            properCase.setFloat(3, coffeePriceTotal - purchasedPortionTotal);
            properCase.execute();

            // end transaction
            conn.commit();
            conn.setAutoCommit(true);
            return properCase.getInt(1);

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
    public int deleteSale(int purchaseId) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Sale getSale(int purchaseId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateSale(Sale ssale) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    
}
