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

public class SaleDao implements ISaleDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public int addSale(Sale sale) throws SQLException {
        // task 12 implementation
        int recordsCount = sale.getRecords().size();
        Integer [] coffeeIds = new Integer[recordsCount];
        Float [] purchasedPortions = new Float[recordsCount];
        Float [] redeemedPortions = new Float[recordsCount];
        
        for (int i = 0; i < recordsCount; i++) {
            coffeeIds[i] = sale.getRecords().get(i).getCoffeeId();
            purchasedPortions[i] = sale.getRecords().get(i).getPurchasedPortion();
            redeemedPortions[i] = sale.getRecords().get(i).getRedeemedPortion();
        }

        Array coffeeIdsSQLArray = conn.createArrayOf("INTEGER", coffeeIds);
        Array purchasedPortionsSQLArray = conn.createArrayOf("FLOAT", purchasedPortions);
        Array redeemedPortionsSQLArray = conn.createArrayOf("FLOAT", redeemedPortions);
        
        CallableStatement properCase = conn.prepareCall("call add_customer_sale_with_recorded_store_and_coffee( ?, ?, ?, ?, ?, ?, ? ) ");
        // calling SQL procedure to insert new Sale and Records tuples in db
        properCase.setInt(1, sale.getCustomerId());
        properCase.setInt(2, sale.getRecords().get(0).getStoreNumber());    // this is weird and should probably be storeNum on SALE, but running out of time
        properCase.setTimestamp(3, sale.getPurchasedTime());
        properCase.setArray(4, coffeeIdsSQLArray);
        properCase.setArray(5, purchasedPortionsSQLArray);
        properCase.setArray(6, redeemedPortionsSQLArray);
        // out parameter
        properCase.setInt(7, -1);
        properCase.registerOutParameter(7, Types.INTEGER);

        properCase.execute();
        ResultSet rs = properCase.getResultSet();
        return rs.getInt(7);
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
