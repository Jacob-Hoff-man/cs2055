package services.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import models.Store;

public class StoreDao implements IStoreDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public int addStore(Store store) throws SQLException {
        // String query = "INSERT INTO STORE (store_name, longitude, latitude, store_type) VALUES (?, ?, ?, ?)";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ps.setString(1, store.getStoreName());
        // ps.setFloat(2, store.getLongitude());
        // ps.setFloat(3, store.getLatitude());
        // ps.setString(4, store.getStoreType());

        // return ps.executeUpdate();

        CallableStatement properCase = conn.prepareCall("call add_store( ?, ?, ?, ? )");
        // calling SQL procedure to insert new store
        properCase.setString(1, store.getStoreName());
        properCase.setFloat(2, store.getLongitude());
        properCase.setFloat(3, store.getLatitude());
        properCase.setString(4, store.getStoreType());
        properCase.execute();
        
        properCase = conn.prepareCall("{ ? = call get_store_number( ? ) }");
        // calling SQL function to get the newly inserted store's store_number for ret
        properCase.registerOutParameter(1, Types.INTEGER);
        properCase.setString(2, store.getStoreName());
        properCase.execute();
        return properCase.getInt(1);
    }

    @Override
    public int deleteStore(int storeNumber) throws SQLException {
        String query = "DELETE FROM STORE WHERE store_number = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, storeNumber);
        
        return ps.executeUpdate();
    }

    @Override
    public Store getStore(int storeNumber) throws SQLException {
        String query = "SELECT * FROM STORE WHERE store_number = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, storeNumber);
        Store store = new Store();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            store.setStoreNumber(rs.getInt("store_number"));
            store.setStoreName(rs.getString("store_name"));
            store.setLongitude(rs.getFloat("longitude"));
            store.setLatitude(rs.getFloat("latitude"));
            store.setStoreType(rs.getString("store_type"));
        }

        if (flag) {
            return store; 
        } else {
            return null;
        }
    }

    @Override
    public Store getStore(String storeName) throws SQLException {
        String query = "SELECT * FROM STORE WHERE store_name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, storeName);
        Store store = new Store();
        ResultSet rs = ps.executeQuery();
        boolean flag = false;

        while (rs.next()) {
            flag = true;
            store.setStoreNumber(rs.getInt("store_number"));
            store.setStoreName(rs.getString("store_name"));
            store.setLongitude(rs.getFloat("longitude"));
            store.setLatitude(rs.getFloat("latitude"));
            store.setStoreType(rs.getString("store_type"));
        }

        if (flag) {
            return store; 
        } else {
            return null;
        }
    }

    @Override
    public List<Store> getStores() throws SQLException {
        String query = "SELECT * FROM STORE";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Store> stores = new ArrayList<>();

        while (rs.next()) {
            Store store = new Store();
            store.setStoreNumber(rs.getInt("store_number"));
            store.setStoreName(rs.getString("store_name"));
            store.setLongitude(rs.getFloat("longitude"));
            store.setLatitude(rs.getFloat("latitude"));
            store.setStoreType(rs.getString("store_type"));
            stores.add(store);
        }

        return stores;
    }

    public List<Store> getStoresWithPromotions() throws SQLException {
        String query = "SELECT * FROM STORE WHERE store_number IN (SELECT DISTINCT store_number FROM OFFERS)";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Store> stores = new ArrayList<>();

        while (rs.next()) {
            Store store = new Store();
            store.setStoreNumber(rs.getInt("store_number"));
            store.setStoreName(rs.getString("store_name"));
            store.setLongitude(rs.getFloat("longitude"));
            store.setLatitude(rs.getFloat("latitude"));
            store.setStoreType(rs.getString("store_type"));
            stores.add(store);
        }

        return stores;
    }

    public List<Store> getStoresWithPromotionsByCoffeeId(int coffeeId) throws SQLException {
        String query = "SELECT * FROM STORE WHERE store_number IN (SELECT DISTINCT store_number FROM OFFERS WHERE promo_number IN (SELECT DISTINCT promo_number FROM INCLUDES WHERE coffee_id = ?))";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, coffeeId);
        ResultSet rs = ps.executeQuery();
        List<Store> stores = new ArrayList<>();

        while (rs.next()) {
            Store store = new Store();
            store.setStoreNumber(rs.getInt("store_number"));
            store.setStoreName(rs.getString("store_name"));
            store.setLongitude(rs.getFloat("longitude"));
            store.setLatitude(rs.getFloat("latitude"));
            store.setStoreType(rs.getString("store_type"));
            stores.add(store);
        }

        return stores;
    }

    @Override
    public void updateStore(Store store) throws SQLException {
        String query = "UPDATE STORE SET store_name=?, longitude=?, latitude=?, store_type=? WHERE store_number=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, store.getStoreName());
        ps.setFloat(2, store.getLongitude());
        ps.setFloat(3, store.getLatitude());
        ps.setString(4, store.getStoreType());
        ps.setInt(5, store.getStoreNumber());
        ps.executeUpdate();
    }
}