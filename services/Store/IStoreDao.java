package services.Store;

import java.sql.SQLException;
import java.util.List;

import models.Store;

public interface IStoreDao {
    public int addStore(Store store) throws SQLException;
    public int deleteStore(int storeNumber) throws SQLException;
    public Store getStore(int storeNumber) throws SQLException;
    public Store getStore(String storeName) throws SQLException;
    public List<Store> getStores() throws SQLException;
    public void updateStore(Store store) throws SQLException;
}
