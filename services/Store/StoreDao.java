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
        // // jdbc implementation
        // String query = "INSERT INTO STORE (store_name, longitude, latitude, store_type) VALUES (?, ?, ?, ?)";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ps.setString(1, store.getStoreName());
        // ps.setFloat(2, store.getLongitude());
        // ps.setFloat(3, store.getLatitude());
        // ps.setString(4, store.getStoreType());
        // return ps.executeUpdate();

        // task 1 implementation
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
        // // jdbc implementation
        // String query = "SELECT * FROM STORE WHERE store_number IN (SELECT DISTINCT store_number FROM OFFERS)";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ResultSet rs = ps.executeQuery();
        // List<Store> stores = new ArrayList<>();

        // while (rs.next()) {
        //     Store store = new Store();
        //     store.setStoreNumber(rs.getInt("store_number"));
        //     store.setStoreName(rs.getString("store_name"));
        //     store.setLongitude(rs.getFloat("longitude"));
        //     store.setLatitude(rs.getFloat("latitude"));
        //     store.setStoreType(rs.getString("store_type"));
        //     stores.add(store);
        // }

        // return stores;

        // task 5 implementation
        // calling SQL function to get query table of stores with promotions for ret
        CallableStatement properCase = conn.prepareCall("{ ? = call get_stores_with_promotions() }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        List<Store> stores = new ArrayList<>();
        try {
            conn.setAutoCommit(false);  
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
            while (rs.next()) {
                Store store = new Store();
                store.setStoreNumber(rs.getInt("store_number"));
                store.setStoreName(rs.getString("store_name"));
                store.setLongitude(rs.getFloat("longitude"));
                store.setLatitude(rs.getFloat("latitude"));
                store.setStoreType(rs.getString("store_type"));
                stores.add(store);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return stores;
    }

    public List<Store> getStoresWithPromotionsByCoffeeId(int coffeeId) throws SQLException {
        // // jdbc implementation
        // String query = "SELECT * FROM STORE WHERE store_number IN (SELECT DISTINCT store_number FROM OFFERS WHERE promo_number IN (SELECT DISTINCT promo_number FROM INCLUDES WHERE coffee_id = ?))";
        // PreparedStatement ps = conn.prepareStatement(query);
        // ps.setInt(1, coffeeId);
        // ResultSet rs = ps.executeQuery();
        // List<Store> stores = new ArrayList<>();

        // while (rs.next()) {
        //     Store store = new Store();
        //     store.setStoreNumber(rs.getInt("store_number"));
        //     store.setStoreName(rs.getString("store_name"));
        //     store.setLongitude(rs.getFloat("longitude"));
        //     store.setLatitude(rs.getFloat("latitude"));
        //     store.setStoreType(rs.getString("store_type"));
        //     stores.add(store);
        // }

        // return stores;
        //task 5 implementation
        // calling SQL function to get query table of stores with promotions by coffee id for ret
        CallableStatement properCase = conn.prepareCall("{ ? = call get_stores_with_promotions_by_coffee_id( ? ) }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setInt(2, coffeeId);
        List<Store> stores = new ArrayList<>();
        try {
            conn.setAutoCommit(false);  
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
            while (rs.next()) {
                Store store = new Store();
                store.setStoreNumber(rs.getInt("store_number"));
                store.setStoreName(rs.getString("store_name"));
                store.setLongitude(rs.getFloat("longitude"));
                store.setLatitude(rs.getFloat("latitude"));
                store.setStoreType(rs.getString("store_type"));
                stores.add(store);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return stores;
    }

    public List<Store> getClosestStores(float latitude, float longitude) throws SQLException {
        // task 7 implementation
        // calling SQL function to get query table of stores closest to a given location for ret
        CallableStatement properCase = conn.prepareCall("{ ? = call get_closest_stores( ?, ? ) }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setFloat(2, latitude);
        properCase.setFloat(3, longitude);
        List<Store> stores = new ArrayList<>();
        try {
            conn.setAutoCommit(false);  
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
            while (rs.next()) {
                Store store = new Store();
                store.setStoreNumber(rs.getInt("store_number"));
                store.setStoreName(rs.getString("store_name"));
                store.setLongitude(rs.getFloat("longitude"));
                store.setLatitude(rs.getFloat("latitude"));
                store.setStoreType(rs.getString("store_type"));
                stores.add(store);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return stores;
    }

    public List<Store> getClosestStoresByPromoNumber(float latitude, float longitude, int promoNumber) throws SQLException {
        //task 7 implementation
        // calling SQL function to get query table of stores by coffee id closest to a given location for ret
        CallableStatement properCase = conn.prepareCall("{ ? = call get_closest_stores_by_promo_number( ?, ?, ? ) }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setFloat(2, latitude);
        properCase.setFloat(3, longitude);
        properCase.setInt(4, promoNumber);
        List<Store> stores = new ArrayList<>();
        try {
            conn.setAutoCommit(false);  
            properCase.execute();
            ResultSet rs = (ResultSet)properCase.getObject(1);
            while (rs.next()) {
                Store store = new Store();
                store.setStoreNumber(rs.getInt("store_number"));
                store.setStoreName(rs.getString("store_name"));
                store.setLongitude(rs.getFloat("longitude"));
                store.setLatitude(rs.getFloat("latitude"));
                store.setStoreType(rs.getString("store_type"));
                stores.add(store);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return stores;
    }

    public List<Integer> getTopKStoresByHighestRevenueInXMonths(int k, int months) throws SQLException {
        CallableStatement properCase = conn.prepareCall("{ ? = call get_top_k_stores_by_highest_revenue_in_x_months( ?, ? ) }");
        properCase.registerOutParameter(1, Types.REF_CURSOR);
        properCase.setInt(2, k);
        properCase.setInt(3, months);
        List<Integer> storeNumbers = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
                properCase.execute();
                ResultSet rs = (ResultSet)properCase.getObject(1);
                while (rs.next()) {
                    int storeNumber = rs.getInt("store_number");
                    storeNumbers.add(storeNumber);
                }
            conn.setAutoCommit(true);
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println(e2.toString());
            }
        }
        return storeNumbers;
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