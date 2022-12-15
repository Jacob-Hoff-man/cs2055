package tests;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import db.TasksDriver;
import models.Coffee;
import models.Customer;
import models.LoyaltyProgram;
import models.Promotion;
import models.Record;
import models.Sale;
import models.Store;
import services.Coffee.CoffeeDao;
import services.Customer.CustomerDao;
import services.LoyaltyProgram.LoyaltyProgramDao;
import services.Promotion.PromotionDao;
import services.Store.StoreDao;

// all tasks tests assume empty tables or insertions from the sample_date.sql file as an initial db state 
public class TasksTests {
    // task 1
    // Produce an error message if a store with the same name already exists.
    public static boolean task1TestCase1() {
        DBConnection.restartDatabaseState();
        Store store = new Store();
        store.setStoreName("CB1");
        // db will throw SQLException when store_type IC is violated, which causes task 1 to output -1
        // check if task1 outputs -1 when existing store name is entered
        return TasksDriver.task1(store) == -1;
    }

    // Assign a unique store number for the new store.
    public static boolean task1TestCase2() {
        DBConnection.restartDatabaseState();
        Store store = new Store();
        store.setStoreName("TASK_1_TEST_CASE_2");
        store.setLongitude(100);
        store.setLatitude(100);
        store.setStoreType("kiosk");
        // db will return the store number, which is acquired by querying for stores with added store's store_number
        // check if task1 outputs > 0 when a store is inserted
        int storeNumber = TasksDriver.task1(store);
        return (storeNumber > 0);

    }

    // Insert all the supplied information and the store number into the database.
    // Display the store number as a confirmation of successfully adding the new store in the database
    public static boolean task1TestCase3() {
        DBConnection.restartDatabaseState();
        Store store = new Store();
        store.setStoreName("TASK_1_TEST_CASE_3");
        store.setLongitude(100);
        store.setLatitude(100);
        store.setStoreType("kiosk");

        // get the store number returns from task1
        int storeNumber = TasksDriver.task1(store);
        store.setStoreNumber(storeNumber);

        // verify the store number is associated with the newly inserted store
        StoreDao storeDao = new StoreDao();
        try {
            // get the inserted Store from db
            Store newStore = storeDao.getStore(storeNumber);
            // check if db copy of Store is equivalent to the original obj
            return store.equals(newStore);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }        
    }

    // task 2
    // Assign a unique coffee ID for the new coffee.
    public static boolean task2TestCase1() {
        DBConnection.restartDatabaseState();
        Coffee coffee = new Coffee();
        coffee.setCoffeeName("TASK_2_TEST_CASE_1");
        coffee.setDescription("TASK_2_TEST_CASE_1");
        coffee.setCountry("TASK_2_TEST_CASE_1");
        coffee.setIntensity(1);
        coffee.setPrice(1);
        coffee.setRedeemPoints(1);
        coffee.setRewardPoints(1);
        // db will return the coffee id, which is acquired by querying for coffees with added coffee's coffee id
        // check if task1 outputs > 0 when a coffee is inserted
        int coffeeId = TasksDriver.task2(coffee);
        return (coffeeId > 0);

    }
    
    // Insert all the supplied information and the coffee ID into the database.
    // Display the coffee ID as a confirmation of successfully adding the new coffee in the database.
    public static boolean task2TestCase2() {
        DBConnection.restartDatabaseState();
        Coffee coffee = new Coffee();
        coffee.setCoffeeName("TASK_2_TEST_CASE_2");
        coffee.setDescription("TASK_2_TEST_CASE_2");
        coffee.setCountry("TASK_2_TEST_CASE_2");
        coffee.setIntensity(1);
        coffee.setPrice(1);
        coffee.setRedeemPoints(1);
        coffee.setRewardPoints(1);

        // get the coffeeId from task2
        int coffeeId = TasksDriver.task2(coffee);
        coffee.setCoffeeId(coffeeId);

        // verify the coffee id is associated with the newly inserted coffee
        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            // get the inserted Coffee from db
            Coffee newCoffee = coffeeDao.getCoffee(coffeeId);
            // check if db copy of Coffee is equivalent to the original obj
            return coffee.equals(newCoffee);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }      
    }

    // task 3
    // Assign a unique promotion ID for the new promotion.
    public static boolean task3TestCase1() {
        DBConnection.restartDatabaseState();
        Promotion promotion = new Promotion();
        promotion.setPromoName("TASK_3_TEST_CASE_1");
        promotion.setStartDate(Date.valueOf("2022-10-01"));
        promotion.setEndDate(Date.valueOf("2022-10-01"));

        int promoNumber = TasksDriver.task3(promotion, 1);

        return (promoNumber > 0);

    }
    // Insert all the supplied information and the promotion ID into the database.
    // Display the promotion ID as a confirmation of successfully scheduling the new promotion in
    // the database.
    public static boolean task3TestCase2() {
        DBConnection.restartDatabaseState();
        Promotion promotion = new Promotion();
        promotion.setPromoName("TASK_3_TEST_CASE_1");
        promotion.setStartDate(Date.valueOf("2022-10-01"));
        promotion.setEndDate(Date.valueOf("2022-10-01"));

        int promoNumber = TasksDriver.task3(promotion, 1);
        promotion.setPromoNumber(promoNumber);

        // verify the promo id is associated with the newly inserted promo
        PromotionDao promotionDao = new PromotionDao();
        try {
            // get the inserted Promotion from db
            Promotion newPromotion = promotionDao.getPromotion(promoNumber);
            // check if db copy of Coffee is equivalent to the original obj
            return promotion.equals(newPromotion);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }      
    }

    // task 4
    // Insert all the supplied information into the database.
    public static boolean task4TestCase1() {
        DBConnection.restartDatabaseState();
        // add a new promotion for the test
        Promotion promotion = new Promotion();
        promotion.setPromoName("TASK_4_TEST_CASE_1");
        promotion.setStartDate(Date.valueOf("2022-10-01"));
        promotion.setEndDate(Date.valueOf("2022-10-01"));

        int promoNumber = TasksDriver.task3(promotion, 1);
        // return if new promo failed to insert into db
        if (promoNumber <= 0) return false;
        // verify the storeNumber != -1, and is > 0 (valid value in range of possible storeNumber generated)
        int storeNumber = TasksDriver.task4(promoNumber, 1);
        return (storeNumber > 0);

    }
    // Display the store ID as a confirmation of successfully offering the promotion in the database.
    public static boolean task4TestCase2() {
        DBConnection.restartDatabaseState();
        // add a new promotion for the test
        Promotion promotion = new Promotion();
        promotion.setPromoName("TASK_4_TEST_CASE_1");
        promotion.setStartDate(Date.valueOf("2022-10-01"));
        promotion.setEndDate(Date.valueOf("2022-10-01"));

        int promoNumber = TasksDriver.task3(promotion, 1);
        // return if new promo failed to insert into db
        if (promoNumber <= 0) return false;

        int storeNumber = TasksDriver.task4(promoNumber, 1);
        if (storeNumber <= 0) return false;

        // verify the storeNumber refers to a store that exists in the db
        StoreDao storeDao = new StoreDao();
        try {
            // get the inserted Store from db
            Store newStore = storeDao.getStore(storeNumber);
            // check if db copy of Store is not null
            if (newStore == null) return false;
            // check if db copy of Store has the same storeNumber
            return newStore.getStoreNumber() == storeNumber;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }     
    }

    // task 5
    // Ask the user to specify if the query should list the stores promoting any coffee or for a specific
    // coffee. In the latter case, the user should supply the coffee ID.
    // list the stores promoting any coffee
    public static boolean task5TestCase1() {
        DBConnection.restartDatabaseState();
        //expected values
        List<Store> eStores = new ArrayList<>();
        Store temp1 = new Store();
        Store temp2 = new Store();
        Store temp3 = new Store();
        // store 1
        temp1.setStoreNumber(1);
        temp1.setStoreName("CB1");
        temp1.setLongitude((float) 20.1);
        temp1.setLatitude((float) 50.2);
        temp1.setStoreType("kiosk");
        eStores.add(temp1);

        // store 2
        temp2.setStoreNumber(2);
        temp2.setStoreName("CB2");
        temp2.setLongitude((float) 50.1);
        temp2.setLatitude((float) 100.2);
        temp2.setStoreType("kiosk");
        eStores.add(temp2);

        // store 3
        temp3.setStoreNumber(3);
        temp3.setStoreName("CB3");
        temp3.setLongitude((float) 10.1);
        temp3.setLatitude((float) 25.2);
        temp3.setStoreType("sitting");
        eStores.add(temp3);

        StoreDao storeDao = new StoreDao();
        PromotionDao promotionDao = new PromotionDao();
        try {
            List<Store> stores = storeDao.getStoresWithPromotions();
            // check if the returned list of stores equals the expected list
            if (!stores.equals(eStores)) return false;
            // add a new store to the db with no promotions
            Store store = new Store();
            store.setStoreName("TASK_5_TEST_CASE_1");
            store.setLongitude(100);
            store.setLatitude(100);
            store.setStoreType("kiosk");
            int storeNumber = storeDao.addStore(store);
            store.setStoreNumber(storeNumber);

            // obtain ret list again, check if the returned list of stores equals the expected list
            stores = storeDao.getStoresWithPromotions();
            if (!stores.equals(eStores)) return false;

            // add a new promotion with offering (at new store) to the db
            Promotion promotion = new Promotion();
            promotion.setPromoName("TASK_5_TEST_CASE_1");
            promotion.setStartDate(Date.valueOf("2022-10-01"));
            promotion.setEndDate(Date.valueOf("2022-10-10"));
            int promoNumber = promotionDao.addPromotionWithIncludedCoffee(promotion, 1);
            promotion.setPromoNumber(promoNumber);
            promotionDao.addPromotionWithOfferedStore(promoNumber, storeNumber);

            // update expected values to include new promotion with offered store
            eStores.add(store);

            // obtain ret list again, check if the returned list of stores equals the expected list
            stores = storeDao.getStoresWithPromotions();
            return stores.equals(eStores);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }


    public static boolean task5TestCase2() {
        DBConnection.emptyDatabaseState();
        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of stores is empty (db is currently empty)
            List<Store> stores = storeDao.getStoresWithPromotions();
            return stores.isEmpty();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // list the stores promoting specific coffee.
    public static boolean task5TestCase3() {
       DBConnection.restartDatabaseState();
        //expected values
        List<Store> eStores = new ArrayList<>();
        Store temp1 = new Store();
        Store temp2 = new Store();
        Store temp3 = new Store();
        // store 1
        temp1.setStoreNumber(1);
        temp1.setStoreName("CB1");
        temp1.setLongitude((float) 20.1);
        temp1.setLatitude((float) 50.2);
        temp1.setStoreType("kiosk");
        eStores.add(temp1);
        // store 2
        temp2.setStoreNumber(2);
        temp2.setStoreName("CB2");
        temp2.setLongitude((float) 50.1);
        temp2.setLatitude((float) 100.2);
        temp2.setStoreType("kiosk");
        eStores.add(temp2);
        // store 3
        temp3.setStoreNumber(3);
        temp3.setStoreName("CB3");
        temp3.setLongitude((float) 10.1);
        temp3.setLatitude((float) 25.2);
        temp3.setStoreType("sitting");
        eStores.add(temp3);

        StoreDao storeDao = new StoreDao();
        PromotionDao promotionDao = new PromotionDao();
        try {
            List<Store> stores = storeDao.getStoresWithPromotionsByCoffeeId(1);
            // check if the returned list of stores equals the expected list
            if (!stores.equals(eStores)) return false;
            // add a new store to the db with no promotions
            Store store = new Store();
            store.setStoreName("TASK_5_TEST_CASE_2");
            store.setLongitude(100);
            store.setLatitude(100);
            store.setStoreType("kiosk");
            int storeNumber = storeDao.addStore(store);
            store.setStoreNumber(storeNumber);

            // obtain ret list again, check if the returned list of stores equals the expected list
            stores = storeDao.getStoresWithPromotionsByCoffeeId(1);
            if (!stores.equals(eStores)) return false;

            // obtain ret list again for coffeeId that has been included 0 times, check if the ret list of stores is empty
            stores = storeDao.getStoresWithPromotionsByCoffeeId(10);
            if (!stores.isEmpty()) return false;

            // add a new promotion with included coffee (that has been included 0 times) to the db
            Promotion promotion = new Promotion();
            promotion.setPromoName("TASK_5_TEST_CASE_1");
            promotion.setStartDate(Date.valueOf("2022-10-01"));
            promotion.setEndDate(Date.valueOf("2022-10-10"));
            int promoNumber = promotionDao.addPromotionWithIncludedCoffee(promotion, 10);
            promotion.setPromoNumber(promoNumber);
            promotionDao.addPromotionWithOfferedStore(promoNumber, storeNumber);

            // update expected values to include only new promotion with offered store
            eStores.clear();
            eStores.add(store);
            
            // obtain ret list again, check if the returned list of stores equals the expected list
            stores = storeDao.getStoresWithPromotionsByCoffeeId(10);
            return stores.equals(eStores);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean task5TestCase4() {
        DBConnection.emptyDatabaseState();
        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of stores is empty (db is currently empty)
            List<Store> stores = storeDao.getStoresWithPromotionsByCoffeeId(1);
            return stores.isEmpty();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // task 6
    // Ask the user to specify if the query should list any promotions at the specified store or only the
    // offers for a specific coffee. In the first case, the user should supply the store ID. In the second
    // case, the user should supply the store ID and coffee ID.
    //list the promotions offered by a specific store.
    public static boolean task6TestCase1() {
        DBConnection.restartDatabaseState();
        // expected values
        List<Promotion> ePromotions = new ArrayList<>();
        // Promo 1
        Promotion promotion1 = new Promotion();
        promotion1.setPromoNumber(1);
        promotion1.setPromoName("Promo 1");
        promotion1.setStartDate(Date.valueOf("2022-10-01"));
        promotion1.setEndDate(Date.valueOf("2023-11-01"));
        ePromotions.add(promotion1);
        // Promo 2
        Promotion promotion2 = new Promotion();
        promotion2.setPromoNumber(2);
        promotion2.setPromoName("Promo 2");
        promotion2.setStartDate(Date.valueOf("2021-10-01"));
        promotion2.setEndDate(Date.valueOf("2023-12-30"));
        ePromotions.add(promotion2);
        // Promo 3
        Promotion promotion3 = new Promotion();
        promotion3.setPromoNumber(3);
        promotion3.setPromoName("Promo 3");
        promotion3.setStartDate(Date.valueOf("2023-01-01"));
        promotion3.setEndDate(Date.valueOf("2023-07-01"));
        ePromotions.add(promotion3);

        PromotionDao promotionDao = new PromotionDao();
        try {
            // return list of promotions, check if ret list of promotions equals the expected values
            List<Promotion> promotions = promotionDao.getPromotionsOfferedByStore(1);
            return promotions.equals(ePromotions);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    public static boolean task6TestCase2() {
        DBConnection.emptyDatabaseState();
        PromotionDao promotionDao = new PromotionDao();
        try {
            // check if ret list of promotions is empty (db is currently empty)
            List<Promotion> promotions = promotionDao.getPromotionsOfferedByStore(1);
            return promotions.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // list the promotions offered by a specific store and coffee id
    public static boolean task6TestCase3() {
        DBConnection.restartDatabaseState();
        // expected values
        List<Promotion> ePromotions = new ArrayList<>();
        // Promo 1
        Promotion promotion1 = new Promotion();
        promotion1.setPromoNumber(3);
        promotion1.setPromoName("Promo 3");
        promotion1.setStartDate(Date.valueOf("2023-01-01"));
        promotion1.setEndDate(Date.valueOf("2023-07-01"));
        ePromotions.add(promotion1);

        PromotionDao promotionDao = new PromotionDao();
        try {
            // re return list of promotions, check if ret list of promotions equals the expected values
            List<Promotion> promotions = promotionDao.getPromotionsOfferedByStoreByCoffeeId(1, 1);
            return promotions.equals(ePromotions);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    public static boolean task6TestCase4() {
        DBConnection.emptyDatabaseState();
        PromotionDao promotionDao = new PromotionDao();
        try {
            // check if ret list of promotions is empty (db is currently empty)
            List<Promotion> promotions = promotionDao.getPromotionsOfferedByStoreByCoffeeId(1, 1);
            return promotions.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // task 7
    // Ask the user to specify if the query should list any closest store or the closest stores offering a
    // specific promotion. In the first case, the user should supply a GPS location (lat, lon). In the
    // second case, the user should supply a GPS location (lat, lon) and promotion ID.
    // list the closest stores
    public static boolean task7TestCase1() {
        DBConnection.restartDatabaseState();
        // expected values
        List<Store> eStores = new ArrayList<>();
        // store 1
        Store store1 = new Store();
        store1.setStoreNumber(3);
        store1.setStoreName("CB3");
        store1.setLongitude((float) 10.1);
        store1.setLatitude((float) 25.2);
        store1.setStoreType("sitting");
        eStores.add(store1);

        StoreDao storeDao = new StoreDao();
        try {
            List<Store> stores = storeDao.getClosestStores(0, 0);
            // check if ret list of stores equals the expected value 
            return stores.equals(eStores);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean task7TestCase2() {
        DBConnection.emptyDatabaseState();
        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of stores is empty (db is currently empty)
            List<Store> stores = storeDao.getClosestStores(0, 0);
            return stores.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }
    // list the closest stores offering promotion by promotion id
    public static boolean task7TestCase3() {
        DBConnection.restartDatabaseState();
        
        List<Store> eStores = new ArrayList<>();
        // expected values
        // store 1
        Store store1 = new Store();
        store1.setStoreNumber(3);
        store1.setStoreName("CB3");
        store1.setLongitude((float) 10.1);
        store1.setLatitude((float) 25.2);
        store1.setStoreType("sitting");
        eStores.add(store1);

        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of stores equals the new expected values
            List<Store> stores = storeDao.getClosestStoresByPromoNumber(0, 0, 1);
            return stores.equals(eStores);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean task7TestCase4() {
        DBConnection.emptyDatabaseState();
        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of stores is empty (db is currently empty)
            List<Store> stores = storeDao.getClosestStoresByPromoNumber(0, 0, 1);
            return stores.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    //  In the event of a tie, equidistant
    // stores, all of the stores that are equidistantly the closest should be displayed.
    public static boolean task7TestCase5() {
        DBConnection.restartDatabaseState();

        StoreDao storeDao = new StoreDao();
        try {
            // add 2 new store to the db with exact same lat, lon
            // Store 1
            Store newStore1 = new Store();
            newStore1.setStoreName("TASK_7_TEST_CASE_3_A");
            newStore1.setLongitude((float) 5.0);
            newStore1.setLatitude((float) 5.0);
            newStore1.setStoreType("sitting");
            int storeNumber1 = storeDao.addStore(newStore1);
            newStore1.setStoreNumber(storeNumber1);
            // Store 2
            Store newStore2 = new Store();
            newStore2.setStoreName("TASK_7_TEST_CASE_3_B");
            newStore2.setLongitude((float) 5.0);
            newStore2.setLatitude((float) 5.0);
            newStore2.setStoreType("sitting");
            int storeNumber2 = storeDao.addStore(newStore2);
            newStore2.setStoreNumber(storeNumber2);

            // expected values -- result should be the two added stores
            List<Store> eStores = new ArrayList<>();
            eStores.add(newStore1);
            eStores.add(newStore2);
            // re return ret list, then check if ret list of stores equals the new expected values
            List<Store> stores = storeDao.getClosestStores(0, 0);

            return stores.equals(eStores);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean task7TestCase6() {
        DBConnection.restartDatabaseState();

        StoreDao storeDao = new StoreDao();
        PromotionDao promotionDao = new PromotionDao();
        try {
            // add 2 new store to the db with exact same lat, lon
            // Store 1
            Store newStore1 = new Store();
            newStore1.setStoreName("TASK_7_TEST_CASE_3_A");
            newStore1.setLongitude((float) 5.0);
            newStore1.setLatitude((float) 5.0);
            newStore1.setStoreType("sitting");
            int storeNumber1 = storeDao.addStore(newStore1);
            newStore1.setStoreNumber(storeNumber1);
            // Store 2
            Store newStore2 = new Store();
            newStore2.setStoreName("TASK_7_TEST_CASE_3_B");
            newStore2.setLongitude((float) 5.0);
            newStore2.setLatitude((float) 5.0);
            newStore2.setStoreType("sitting");
            int storeNumber2 = storeDao.addStore(newStore2);
            newStore2.setStoreNumber(storeNumber2);

            // offer promo (with promo number = 1) at both of the new stores
            promotionDao.addOffers(1, storeNumber1);
            promotionDao.addOffers(1, storeNumber2);

            // expected values -- result should be the two added stores
            List<Store> eStores = new ArrayList<>();
            eStores.add(newStore1);
            eStores.add(newStore2);

            // re return ret list, then check if ret list of stores equals the new expected values
            List<Store> stores = storeDao.getClosestStoresByPromoNumber(0, 0, 1);

            return stores.equals(eStores);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // task 8
    // If the member level doesn’t exist, the member level and its booster factor are inserted.
    public static boolean task8TestCase1() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("test");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)1.9);
        loyaltyProgram.setBoosterValue((float)1.9);

        return TasksDriver.task8(loyaltyProgram) == 1;

    } 
    // If the member level exists, the booster factor is updated.
    public static boolean task8TestCase2() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("gold");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)1.9);
        loyaltyProgram.setBoosterValue((float)1.9);

        return TasksDriver.task8(loyaltyProgram) == 1;

    }
    // If the loyalty level is not an accepted string, should fail
    public static boolean task8TestCase3() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("invalid_loyalty_level_string");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)1.9);
        loyaltyProgram.setBoosterValue((float)1.9);
        // check if task returns -1
        return TasksDriver.task8(loyaltyProgram) == -1;

    } 
    // If the total points value unlocked at is <= 0, should fail
    public static boolean task8TestCase4() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("invalid_loyalty_level_string");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)-10.0);
        loyaltyProgram.setBoosterValue((float)1.9);
        // check if task returns -1
        return TasksDriver.task8(loyaltyProgram) == -1;

    } 
    // If the booster value is < 1 or > 4, should fail
    public static boolean task8TestCase5() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("invalid_loyalty_level_string");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)50.0);
        loyaltyProgram.setBoosterValue((float)1.9);
        // check if task returns -1
        return TasksDriver.task8(loyaltyProgram) == -1;

    } 

    public static boolean task8TestCase6() {
        DBConnection.restartDatabaseState();
        
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel("invalid_loyalty_level_string");
        loyaltyProgram.setTotalPointsValueUnlockedAt((float)0.0);
        loyaltyProgram.setBoosterValue((float)1.9);
        // check if task returns -1
        return TasksDriver.task8(loyaltyProgram) == -1;

    } 

    // task 9
    // insert a new customer into the db
    public static boolean task9TestCase1() {
        DBConnection.restartDatabaseState();
        Customer customer = new Customer();
        customer.setFirstName("TASK_9_TEST_CASE_1");
        customer.setLastName("TASK_9_TEST_CASE_1");
        customer.setMidInitial("1");
        customer.setBirthDay("1");
        customer.setBirthMonth("jan");
        customer.setPhoneNumber("814-814-8112");
        customer.setPhoneType("mobile");
        // insert new customer into db, check that an id (value > 0) is returned
        return TasksDriver.task9(customer) > 0;
    }

    // loyalty level should be set to ‘basic’ since no reward points have been earned yet.
    public static boolean task9TestCase2() {
        DBConnection.restartDatabaseState();
        Customer customer = new Customer();
        customer.setFirstName("TASK_9_TEST_CASE_1");
        customer.setLastName("TASK_9_TEST_CASE_1");
        customer.setMidInitial("1");
        customer.setBirthDay("1");
        customer.setBirthMonth("jan");
        customer.setPhoneNumber("814-814-8112");
        customer.setPhoneType("mobile");
        // insert new customer into db, get the returned id
        int customerId = TasksDriver.task9(customer);
        // get new customer
        CustomerDao customerDao = new CustomerDao();
        try {
            Customer newCustomer = customerDao.getCustomer(customerId);
            System.out.println(newCustomer);
            // check if loyalty level is 'basic'
            return newCustomer.getLoyaltyLevel().equals("basic");
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // task 10
    // If the customer exists, check the correct loyalty points are received
    public static boolean task10TestCase1() {
        DBConnection.restartDatabaseState();
        CustomerDao customerDao = new CustomerDao();
        try {
            // expected value
            float eCustomerCurrentPoints = 10000;
            float customerCurrentPoints = (float)customerDao.getCustomerCurrentPoints(1);
            // check the ret current points equals the expected value
            return customerCurrentPoints == eCustomerCurrentPoints;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // If the customer exists, check the correct total points are received
    public static boolean task10TestCase2() {
        DBConnection.restartDatabaseState();
        CustomerDao customerDao = new CustomerDao();
        try {
            // expected value
            float eCustomerTotalPoints = 499999;
            float customerTotalPoints = (float)customerDao.getCustomerTotalPoints(1);
            // check the ret current points equals the expected value
            return customerTotalPoints == eCustomerTotalPoints;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // task 11
    // produce a ranked list of the most valuable Customers
    public static boolean task11TestCase1() {
        DBConnection.restartDatabaseState();

        // expected values -- using only the customer ids for simplifying the test
        int [] eCustomerIds = {7, 2, 20, 13, 14, 15, 16, 17, 18, 19, 10, 11, 12, 8, 6, 4, 5, 1, 3, 9};

        CustomerDao customerDao = new CustomerDao();
        try {
            // get the ret list of customers ranked by current points, grouped by loyalty level
            List<Customer> rankedCustomers = customerDao.getCustomersRankedByPoints();
            // check the ret customers' customer ids (in order) equals the expected customer id values
            for (int i = 0; i < rankedCustomers.size(); i++) {
                if (eCustomerIds[i] != rankedCustomers.get(i).getCustomerId()) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // task 12
    // Assign a unique purchase id for the new sale.
    public static boolean task12TestCase1() {
        DBConnection.restartDatabaseState();
        // expected values
        List<Record> records = new ArrayList<>();
        // Record 1
        Record record1 = new Record();
        record1.setStoreNumber(1);
        record1.setCoffeeId(1);
        record1.setPurchasedPortion((float)1.5);
        record1.setRedeemedPortion((float)45);
        records.add(record1);
        // Record 2
        Record record2 = new Record();
        record2.setStoreNumber(1);
        record2.setCoffeeId(1);
        record2.setPurchasedPortion((float)1.5);
        record2.setRedeemedPortion((float)45);
        records.add(record2);
        // Record 3
        Record record3 = new Record();
        record3.setStoreNumber(1);
        record3.setCoffeeId(1);
        record3.setPurchasedPortion((float)1.5);
        record3.setRedeemedPortion((float)45);
        records.add(record3);

        Sale sale = new Sale();
        sale.setCustomerId(1);
        sale.setPurchasedTime(Timestamp.valueOf("2022-12-15 11:12:19.422280"));
        sale.setRecords(records);
        // db will return the purchase id
        // check if task12 outputs > 0 when a store is inserted
        int purchaseId = TasksDriver.task12(sale);
        return (purchaseId > 0);

    }

    // If the purchase uses points, the purchase should fail if the customer does not have enough
    public static boolean task12TestCase2() {
        DBConnection.restartDatabaseState();
        // expected values
        List<Record> records = new ArrayList<>();
        // Record 1
        Record record1 = new Record();
        record1.setStoreNumber(1);
        record1.setCoffeeId(1);
        record1.setPurchasedPortion((float)1.5);
        record1.setRedeemedPortion((float)45);
        records.add(record1);
        // Record 2
        Record record2 = new Record();
        record2.setStoreNumber(1);
        record2.setCoffeeId(1);
        record2.setPurchasedPortion((float)1.5);
        record2.setRedeemedPortion((float)45);
        records.add(record2);
        // Record 3
        Record record3 = new Record();
        record3.setStoreNumber(1);
        record3.setCoffeeId(1);
        record3.setPurchasedPortion((float)1.5);
        record3.setRedeemedPortion((float)45);
        records.add(record3);

        Sale sale = new Sale();
        sale.setCustomerId(2); // using a customerId with 0 current points
        sale.setPurchasedTime(Timestamp.valueOf("2022-12-15 11:12:19.422280"));
        sale.setRecords(records);
        // db will return the purchase id
        int purchaseId = TasksDriver.task12(sale);
        // check if task12 outputs -1 when customer has insufficient funds
        return (purchaseId == -1);
    }

    // Task 13
    // get the coffees from the db
    public static boolean task13TestCase1() {
        DBConnection.restartDatabaseState();
        // expected value -- using arrays for coffeeId, coffeeName for simplifying test -- both are keys for Coffee
        int [] coffeeIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        String [] coffeeNames = {"Espresso", "Double Espresso", "Red Eye", "Black Eye", "Americano", "Long Black", "Macchiato", "Breve", "Cappuccino", "Cafe Latte" , "Mocha", "Iced Coffee"};

        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            // check if ret list of coffees equals the expected coffeeIds, coffeeNames
            List<Coffee> coffees = coffeeDao.getCoffees();
            for (int i = 0; i < coffees.size(); i++) {
                if (coffeeIds[i] != coffees.get(i).getCoffeeId()) return false;
                if (!coffeeNames[i].equals(coffees.get(i).getCoffeeName())) return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }
    // if there are no coffees available, then the returned list should be empty
    public static boolean task13TestCase2() {
        DBConnection.emptyDatabaseState();
        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            // check if ret list of coffees is empty (db is currently empty)
            List<Coffee> coffees = coffeeDao.getCoffees();
            return coffees.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // Task 14
    // List the IDs and names of all coffees of a specified intensity and each of which has two specified
    // keywords in their names (i.e., both keywords in its name)
    // Display coffee IDs of the provided intensity, each of which has both keywords in the coffee’s
    // name, or a message (e.g., ‘No coffees satisfied these conditions’) if no coffees satisfy the provided
    // intensity or contain both keywords in the coffee’s name.
    public static boolean task14TestCase1() {
        DBConnection.restartDatabaseState();
        // expected value -- using arrays for coffeeId, coffeeName for simplifying test -- both are keys for Coffee
        int [] coffeeIds = {1};
        String [] coffeeNames = {"Espresso"};   // db also contains "Double Espresso", but different intensity

        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            // check if ret list of coffees equals the expected coffeeIds, coffeeNames
            List<Coffee> coffees = coffeeDao.getCoffeesByIntensityAndTwoKeywords(6, "es", "ess");
            for (int i = 0; i < coffees.size(); i++) {
                if (coffeeIds[i] != coffees.get(i).getCoffeeId()) return false;
                if (!coffeeNames[i].equals(coffees.get(i).getCoffeeName())) return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }
    // if there are no coffees available, then the returned list should be empty
    public static boolean task14TestCase2() {
        DBConnection.emptyDatabaseState();
        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            // check if ret list of coffees is empty (db is currently empty)
            List<Coffee> coffees = coffeeDao.getCoffees();
            return coffees.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // task 15
    // List the IDs of the top K stores having the highest revenue for the past X months
    // Revenue is defined as the sum of money that the customer paid for the coffees.
    // 1 month is defined as 30 days counting back starting from the current date time.
    // Display the top K stores in the past X months in sorted order, with the store ID of the store
    // having the highest revenue at the head.
    // all sales, k = 3
    public static boolean task15TestCase1() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eStoreNumber = { 2, 1, 3 };

        StoreDao storeDao = new StoreDao();
        try {
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(3, 15);
            for (int i = 0; i < storeNumbers.size(); i++) {
                if (eStoreNumber[i] != storeNumbers.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // all sales, k = 1
    public static boolean task15TestCase2() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eStoreNumber = { 2 };

        StoreDao storeDao = new StoreDao();
        try {
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(1, 15);
            for (int i = 0; i < storeNumbers.size(); i++) {
                if (eStoreNumber[i] != storeNumbers.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // not all sales (months = < 12-ish), k = 3
    public static boolean task15TestCase3() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eStoreNumber = { 1, 3, 2 };

        StoreDao storeDao = new StoreDao();
        try {
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(3, 6);
            for (int i = 0; i < storeNumbers.size(); i++) {
                if (eStoreNumber[i] != storeNumbers.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // If multiple stores have the same revenue, their order in the returned result can be arbitrary.
    // If multiple stores have the same revenue in the Kth highest revenue position, their store IDs
    // should all be returned.
    // all sales, k = 2, but 3 stores are returns becuase 1, 3 tie for rank 2
    public static boolean task15TestCase4() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eStoreNumber = { 2, 1, 3 };

        StoreDao storeDao = new StoreDao();
        try {
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(2, 15);
            for (int i = 0; i < storeNumbers.size(); i++) {
                if (eStoreNumber[i] != storeNumbers.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // if there is no sales/stores avaiable, then the returned list should be empty
    public static boolean task15TestCase5() {
        DBConnection.emptyDatabaseState();
        StoreDao storeDao = new StoreDao();
        try {
            // check if ret list of coffees is empty (db is currently empty)
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(1, 100);
            return storeNumbers.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    // Task 16
    // List the IDs of the top K customers having spent the most money buying coffee in the past X
    // months
    // 1 month is defined as 30 days counting back starting from the current date time.
    // Display the top K customers in the past X months in sorted order, with the customer ID of
    // the customer having spent the most money at the head.
    public static boolean task16TestCase1() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eCustomerIds = { 20, 1, 5 };

        CustomerDao customerDao = new CustomerDao();
        try {
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(3, 15);
            for (int i = 0; i < customerIds.size(); i++) {
                if (eCustomerIds[i] != customerIds.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    // If multiple customers have the same spending, their order in the returned result can be arbitrary.
    // If multiple customers have the same spending in the Kth highest spending position, their
    // customer IDs should all be returned.

     // all sales, k = 1
     public static boolean task16TestCase2() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eCustomerIds = { 20 };

        CustomerDao customerDao = new CustomerDao();
        try {
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(1, 15);
            for (int i = 0; i < customerIds.size(); i++) {
                if (eCustomerIds[i] != customerIds.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // not all sales (months = < 12-ish), k = 2
    public static boolean task16TestCase3() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eCustomerIds = { 5, 1 };

        CustomerDao customerDao = new CustomerDao();
        try {
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(2, 6);
            System.out.println(customerIds);
            for (int i = 0; i < customerIds.size(); i++) {
                if (eCustomerIds[i] != customerIds.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // all sales, k = 2, but 3 customers are returns becuase 1, 3 tie for rank 2
    public static boolean task16TestCase4() {
        DBConnection.restartDatabaseState();
        // expected values
        int [] eCustomerIds = { 20, 1, 5 };

        CustomerDao customerDao = new CustomerDao();
        try {
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(2, 15);
            for (int i = 0; i < customerIds.size(); i++) {
                if (eCustomerIds[i] != customerIds.get(i)) return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // if there is no sales/stores avaiable, then the returned list should be empty
    public static boolean task16TestCase5() {
        DBConnection.emptyDatabaseState();
        CustomerDao customerDao = new CustomerDao();
        try {
            // check if ret list of coffees is empty (db is currently empty)
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(1, 100);
            return customerIds.isEmpty();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

}