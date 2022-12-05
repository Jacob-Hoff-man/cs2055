package services.Sale;

import java.sql.SQLException;

import models.Sale;

public interface ISaleDao {
    public int addSale(Sale sale) throws SQLException;
    public int deleteSale(int purchaseId) throws SQLException;
    public Sale getSale(int purchaseId) throws SQLException;
    // public List<Sale> getSales(int [] purchaseIds) throws SQLException;
    // public List<Sale> getSales() throws SQLException;
    public void updateSale(Sale ssale) throws SQLException;
}
