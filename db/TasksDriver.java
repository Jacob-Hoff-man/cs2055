package db;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import models.Coffee;
import models.Customer;
import models.LoyaltyProgram;
import models.Promotion;
import models.Record;
import models.Sale;
import models.Store;
import services.ClockDao;
import services.Coffee.CoffeeDao;
import services.Customer.CustomerDao;
import services.LoyaltyProgram.LoyaltyProgramDao;
import services.Promotion.PromotionDao;
import services.Sale.SaleDao;
import services.Store.StoreDao;

public class TasksDriver {
    protected static final String intRegex = "^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$";
    protected static final String floatRegex = "[-+]?[0-9]*\\.?[0-9]+";
    protected static final String[] storeTypes = {"kiosk", "sitting"};

    public static int taskRunner() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Task Number:");
        String taskNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(taskNumber)) {
            System.out.println("Enter Task Number (Int Value Between 1-16 Only):");
            taskNumber = myScanner.nextLine();
        }
        return taskRunner(Integer.parseInt(taskNumber));
    }

    public static int taskRunner(int taskNumber) {
        switch(taskNumber) {
            case 1:
                return TasksDriver.task1();
            case 2:
                return TasksDriver.task2();
            case 3:
                return TasksDriver.task3();
            case 4:
                return TasksDriver.task4();
            case 5:
                return TasksDriver.task5();
            case 6:
                return TasksDriver.task6();
            case 7:
                return TasksDriver.task7();
            case 8:
                return TasksDriver.task8();
            case 9:
                return TasksDriver.task9();
            case 10:
                return TasksDriver.task10();
            case 11:
                return TasksDriver.task11();
            case 12:
                return TasksDriver.task12();
            case 13:
                return TasksDriver.task13();
            case 14:
                return TasksDriver.task14();
            case 15:
                return TasksDriver.task15();
            case 16:
                return TasksDriver.task16();
            default:
                return -1;
        }
    }

    //helpers
    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public static boolean stringIsValidIntValue(String inputStr) {
        return inputStr.matches(intRegex);
    }

    public static boolean stringIsValidFloatValue(String inputStr) {
        return inputStr.matches(floatRegex);
    }

    public static boolean stringIsValidDateValue(String inputStr) {
        try {
            return Date.valueOf(inputStr) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean stringIsValidTimestampValue(String inputStr)
    { 
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        try{
        format.parse(inputStr);
        return true;
        }
        catch(ParseException e)
        {
            return false;
        }
    }

    //tasks
    public static int task1() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Store Name:");
        String storeName = myScanner.nextLine();
        
        System.out.println("Enter Longitude:");
        String longitude = myScanner.nextLine();
        while(!stringIsValidFloatValue(longitude)) {
            System.out.println("Enter Longitude (Float Value Only):");
            longitude = myScanner.nextLine();
        }

        System.out.println("Enter Latitude:");
        String latitude = myScanner.nextLine();
        while(!stringIsValidFloatValue(latitude)) {
            System.out.println("Enter Latitude (Float Value Only):");
            latitude = myScanner.nextLine();
        }

        System.out.println("Enter Store Type:");
        String storeType = myScanner.nextLine();
        while(!stringContainsItemFromList(storeType, storeTypes)) {
            System.out.println("Enter Store Type (Valid Store Types Only):");
            storeType = myScanner.nextLine();
        }

        Store myStore = new Store();
        myStore.setStoreName(storeName);
        myStore.setLongitude(Float.parseFloat(longitude));
        myStore.setLatitude(Float.parseFloat(latitude));
        myStore.setStoreType(storeType);


        StoreDao storeDao = new StoreDao();
        try {
            return storeDao.addStore(myStore);

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#1:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task2() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Coffee Name:");
        String coffeeName = myScanner.nextLine();
        
        System.out.println("Enter Description:");
        String description = myScanner.nextLine();
        
        System.out.println("Enter Country of Origin:");
        String country = myScanner.nextLine();
        
        System.out.println("Enter Intensity:");
        String intensity = myScanner.nextLine();
        while(!stringIsValidIntValue(intensity)) {
            System.out.println("Enter Intensity (Int Value Between 1-12 Only):");
            intensity = myScanner.nextLine();
        }

        System.out.println("Enter Price:");
        String price = myScanner.nextLine();
        while(!stringIsValidFloatValue(price)) {
            System.out.println("Enter Price (Float Value Only):");
            price = myScanner.nextLine();
        }

        System.out.println("Enter Redeem Points:");
        String redeemPoints = myScanner.nextLine();
        while(!stringIsValidFloatValue(redeemPoints)) {
            System.out.println("Enter Redeem Points (Float Value Only):");
            redeemPoints = myScanner.nextLine();
        }

        System.out.println("Enter Reward Points:");
        String rewardPoints = myScanner.nextLine();
        while(!stringIsValidFloatValue(rewardPoints)) {
            System.out.println("Enter Reward Points (Float Value Only):");
            rewardPoints = myScanner.nextLine();
        }

        Coffee coffee = new Coffee();
        coffee.setCoffeeName(coffeeName);
        coffee.setDescription(description);
        coffee.setCountry(country);
        coffee.setIntensity(Integer.parseInt(intensity));
        coffee.setPrice(Float.parseFloat(price));
        coffee.setRedeemPoints(Float.parseFloat(redeemPoints));
        coffee.setRewardPoints(Float.parseFloat(rewardPoints));

        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            return coffeeDao.addCoffee(coffee);
            
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#2:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task3() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Promotion Name:");
        String promoName = myScanner.nextLine();
        
        System.out.println("Enter Start Date:");
        String startDate = myScanner.nextLine();
        while(!stringIsValidDateValue(startDate)) {
            System.out.println("Enter Start Date (yyyy-MM-dd Format Only):");
            startDate = myScanner.nextLine();
        }

        System.out.println("Enter End Date:");
        String endDate = myScanner.nextLine();
        while(!stringIsValidDateValue(endDate)) {
            System.out.println("Enter End Date (yyyy-MM-dd Format Only):");
            endDate = myScanner.nextLine();
        }

        System.out.println("Enter Coffee Id:");
        String coffeeId = myScanner.nextLine();
        while(!stringIsValidIntValue(coffeeId)) {
            System.out.println("Enter Coffee Id (Int Value Only):");
            coffeeId = myScanner.nextLine();
        }

        Promotion promotion = new Promotion();
        promotion.setPromoName(promoName);
        promotion.setStartDate(Date.valueOf(startDate));
        promotion.setEndDate(Date.valueOf(endDate));

        // CoffeeDao coffeeDao = new CoffeeDao();
        PromotionDao promotionDao = new PromotionDao();
        try {
            return promotionDao.addPromotionWithIncludedCoffee(promotion, Integer.parseInt(coffeeId));
            // // jdbc implementation
            // // run only if the coffee exists in db (get by id)
            // Coffee coffee = coffeeDao.getCoffee(Integer.parseInt(coffeeId));
            // if (coffee != null) {
            //     promotionDao.addPromotionWithIncludedCoffee(promotion, coffee.getCoffeeId());
            //     return (promotionDao.getPromotion(promoName)).getPromoNumber();
            // } else {
            //     // no coffee with given id exists
            //     throw new CoffeeByIdDoesNotExistException();
            // }
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#3:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task4() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Promo Number:");
        String promoNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(promoNumber)) {
            System.out.println("Enter Promo Number (Int Value Only):");
            promoNumber = myScanner.nextLine();
        }
        System.out.println("Enter Store Number:");
        String storeNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(storeNumber)) {
            System.out.println("Enter Store Number (Int Value Only):");
            storeNumber = myScanner.nextLine();
        }

        StoreDao storeDao = new StoreDao();
        PromotionDao promotionDao = new PromotionDao();
        try {
            return promotionDao.addPromotionWithOfferedStore(Integer.parseInt(promoNumber), Integer.parseInt(storeNumber));
            // // jdbc implementation
            // // verify the promotion exists
            // Promotion promotion = promotionDao.getPromotion(Integer.parseInt(promoNumber));
            // if (promotion == null) throw new PromotionByIdDoesNotExistException();
            // // verify the store exists
            // Store store = storeDao.getStore(Integer.parseInt(storeNumber));
            // if (store == null) throw new StoreByIdDoesNotExistException();
            
            // return promotionDao.updatePromotionWithOfferedStore(promotion, store.getStoreNumber());

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#4:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task5() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Coffee Id, or leave blank for all Coffees Offered in Promotions:");
        String coffeeId = myScanner.nextLine();
        while(!stringIsValidIntValue(coffeeId)) {
            // allow blank space
            if (coffeeId.isBlank()) break;

            System.out.println("Enter Coffee Id, or leave blank for all Coffees Offered in Promotions (Int Value Only):");
            coffeeId = myScanner.nextLine();
        }

        StoreDao storeDao = new StoreDao();
        try {
            String output;
            if (coffeeId.isBlank()) {
                List<Store> promoStores = storeDao.getStoresWithPromotions();
                output = promoStores.isEmpty() ? 
                    "No stores are currently offering any promotions." : 
                    promoStores.toString();
            } else {
                List<Store> promoStores = storeDao.getStoresWithPromotionsByCoffeeId(Integer.parseInt(coffeeId));
                output = promoStores.isEmpty() ?
                    "No stores are currently promoting this coffee." :
                    promoStores.toString();
            }

            System.out.println(output);
            return 1;
            
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#5:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task6() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Store Number:");
        String storeNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(storeNumber)) {
            System.out.println("Enter Store Number (Int Value Only):");
            storeNumber = myScanner.nextLine();
        }

        System.out.println("Enter Coffee Id, or leave blank for all Coffees Offered in Store's Promotions:");
        String coffeeId = myScanner.nextLine();
        while(!stringIsValidIntValue(coffeeId)) {
            // allow blank space
            if (coffeeId.isBlank()) break;

            System.out.println("Enter Coffee Id, or leave blank for all Coffees Offered in Store's Promotions (Int Value Only):");
            coffeeId = myScanner.nextLine();
        }

        PromotionDao promotionDao = new PromotionDao();
        try {
            String output;
            if (coffeeId.isBlank()) {
                List<Promotion> promotions = promotionDao.getPromotionsOfferedByStore(Integer.parseInt(storeNumber));
                output = promotions.isEmpty() ?
                    "No promotions are currently offered at this store." :
                    promotions.toString();
            } else {
                List<Promotion> promotions = promotionDao.getPromotionsOfferedByStoreByCoffeeId(Integer.parseInt(storeNumber), Integer.parseInt(coffeeId));
                output = promotions.isEmpty() ?
                    "No promotions for this coffee are currently offered at this store." :
                    promotions.toString();
            }

            System.out.println(output);
            return 1;

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#6:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task7() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Current Latitude:");
        String latitude = myScanner.nextLine();
        while(!stringIsValidFloatValue(latitude)) {
            System.out.println("Enter Current Latitude (Float Value Only):");
            latitude = myScanner.nextLine();
        }

        System.out.println("Enter Current Longitude:");
        String longitude = myScanner.nextLine();
        while(!stringIsValidFloatValue(longitude)) {
            System.out.println("Enter Current Longitude (Float Value Only):");
            longitude = myScanner.nextLine();
        }

        System.out.println("Enter Promotion Number, or leave blank for all Promotions Offered in Stores:");
        String promoNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(promoNumber)) {
            // allow blank space
            if (promoNumber.isBlank()) break;

            System.out.println("Enter Promotion Number, or leave blank for all Promotions Offered in Stores(Int Value Only):");
            promoNumber = myScanner.nextLine();
        }

        StoreDao storeDao = new StoreDao();
        try {
            String output;
            if (promoNumber.isBlank()) {
                List<Store> stores = storeDao.getClosestStores(Float.parseFloat(latitude), Float.parseFloat(longitude));
                output = stores.toString();
            } else {
                List<Store> stores = storeDao.getClosestStoresByPromoNumber(Float.parseFloat(latitude), Float.parseFloat(longitude), Integer.parseInt(promoNumber));
                output = stores.toString();
            }

            System.out.println(output);
            return 1;
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#7:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task8() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter Loyalty Level Name:");
        String loyaltyLevel = myScanner.nextLine();

        System.out.println("Enter Loyalty Level's Achieving Point Value:");
        String totalPointsValueUnlockedAt = myScanner.nextLine();
        while(!stringIsValidFloatValue(totalPointsValueUnlockedAt)) {
            System.out.println("Enter Loyalty Level's Achieving Point Value (Float Value Only):");
            totalPointsValueUnlockedAt = myScanner.nextLine();
        }

        System.out.println("Enter Loyalty Level's Booster Value:");
        String boosterValue = myScanner.nextLine();
        while(!stringIsValidFloatValue(boosterValue)) {
            System.out.println("Enter Loyalty Level's Booster Value (Float Value Only):");
            boosterValue = myScanner.nextLine();
        }

        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setLoyaltyLevel(loyaltyLevel);
        loyaltyProgram.setTotalPointsValueUnlockedAt(Float.parseFloat(totalPointsValueUnlockedAt));
        loyaltyProgram.setBoosterValue(Float.parseFloat(boosterValue));

        LoyaltyProgramDao loyaltyProgramDao = new LoyaltyProgramDao();
        try {
            System.out.println(loyaltyProgramDao.addOrUpdateLoyaltyProgram(loyaltyProgram));
            return 1;
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#8:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
        
    }

    public static int task9() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Customer First Name:");
        String firstName = myScanner.nextLine();

        System.out.println("Enter Customer Last Name:");
        String lastName = myScanner.nextLine();
        
        System.out.println("Enter Customer Middle Initial:");
        String midInitial = myScanner.nextLine();
        
        System.out.println("Enter Customer Birth Day:");
        String birthDay = myScanner.nextLine();

        System.out.println("Enter Customer Birth Month:");
        String birthMonth = myScanner.nextLine();

        System.out.println("Enter Customer Phone Number:");
        String phoneNumber = myScanner.nextLine();

        System.out.println("Enter Customer Phone Type:");
        String phoneType= myScanner.nextLine();

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setMidInitial(midInitial);
        customer.setBirthDay(birthDay);
        customer.setBirthMonth(birthMonth);
        customer.setPhoneNumber(phoneNumber);
        customer.setPhoneType(phoneType);

        CustomerDao customerDao = new CustomerDao();
        try {
            return customerDao.addCustomer(customer);

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#9:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task10() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Customer Id:");
        String customerId = myScanner.nextLine();
        while(!stringIsValidIntValue(customerId)) {
            System.out.println("Enter Customer Id (Int Value Only):");
            customerId = myScanner.nextLine();
        }

        CustomerDao customerDao = new CustomerDao();
        try {
            System.out.println("Current Points: " + customerDao.getCustomerCurrentPoints(Integer.parseInt(customerId)));
            System.out.println("Total Points: " + customerDao.getCustomerTotalPoints(Integer.parseInt(customerId)));
            return Integer.parseInt(customerId);

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#10:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task11() {
        CustomerDao customerDao = new CustomerDao();
        try {
            List<Customer> customers = customerDao.getCustomersRankedByPoints();
            System.out.println(customers.toString());
            // returning #1 customer's id
            return customers.get(0).getCustomerId();

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#11:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task12() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter Customer Id:");
        String customerId = myScanner.nextLine();
        while(!stringIsValidIntValue(customerId)) {
            System.out.println("Enter Customer Id (Int Value Only):");
            customerId = myScanner.nextLine();
        }

        System.out.println("Enter Store Number:");
        String storeNumber = myScanner.nextLine();
        while(!stringIsValidIntValue(storeNumber)) {
            System.out.println("Enter Store Number (Int Value Only):");
            storeNumber = myScanner.nextLine();
        }

        System.out.println("Enter Sale's Purchased Time:");
        String purchasedTime = myScanner.nextLine();
        while(!stringIsValidTimestampValue(purchasedTime)) {
            System.out.println("Enter Sale's Purchased Time (yyyy-MM-dd HH:mm:ss.SSSSSS Format Only):");
            purchasedTime = myScanner.nextLine();
        }
        
        List<Record> records = new ArrayList<Record>();
        String flag = "y";
        int coffeeCount = 0;
        while(flag.equals("y")) {
            coffeeCount++;
            System.out.println("Coffee #" + coffeeCount + " Values:");
            
            System.out.println("Enter Coffee Id:");
            String coffeeId = myScanner.nextLine();
            while(!stringIsValidIntValue(coffeeId)) {
                System.out.println("Enter Coffee Id (Int Value Only):");
                coffeeId = myScanner.nextLine();
            }
    
            System.out.println("Enter Coffee's Purchased Portion Value:");
            String purchasedPortion = myScanner.nextLine();
            while(!stringIsValidFloatValue(purchasedPortion)) {
                System.out.println("Enter Coffee's Purchased Portion Value (Float Value Only):");
                purchasedPortion = myScanner.nextLine();
            }
    
            System.out.println("Enter Coffee's Redeemed Portion Value:");
            String redeemedPortion = myScanner.nextLine();
            while(!stringIsValidFloatValue(redeemedPortion)) {
                System.out.println("Enter Coffee's Redeemed Portion Value (Float Value Only):");
                redeemedPortion = myScanner.nextLine();
            }

            Record record = new Record();
            record.setStoreNumber(Integer.parseInt(storeNumber));
            record.setCoffeeId(Integer.parseInt(coffeeId));
            record.setPurchasedPortion(Float.parseFloat(purchasedPortion));
            record.setRedeemedPortion(Float.parseFloat(redeemedPortion));

            records.add(record);

            System.out.println("Enter Another Coffee? (type 'y' for yes)");
            flag = myScanner.nextLine();

        }

        Sale sale = new Sale();
        sale.setCustomerId(Integer.parseInt(customerId));
        sale.setPurchasedTime(Timestamp.valueOf(purchasedTime));
        System.out.println("TIMESTAMP PRINT:" + sale.getPurchasedTime());
        sale.setRecords(records);

        SaleDao saleDao = new SaleDao();
        try {
            return saleDao.addSale(sale);

        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#12:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }

    }

    public static int task13() {
        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            String output;
            List<Coffee> coffees = coffeeDao.getCoffees();
            if (coffees.isEmpty()) {
                output = "No Coffees are currently offered by BoutiqueCoffee.";
            } else {
                output = coffees.toString();
            }

            System.out.println(output);
            return 1;
            
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#13:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task14() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter Intensity:");
        String intensity = myScanner.nextLine();
        while(!stringIsValidIntValue(intensity)) {
            System.out.println("Enter Intensity (Int Value Between 1-12 Only):");
            intensity = myScanner.nextLine();
        }

        System.out.println("Enter Keyword 1 Contained In Coffee Name:");
        String kw1 = myScanner.nextLine();

        System.out.println("Enter Keyword 1 Contained In Coffee Name:");
        String kw2 = myScanner.nextLine();

        CoffeeDao coffeeDao = new CoffeeDao();
        try {
            String output;
            List<Coffee> coffees = coffeeDao.getCoffeesByIntensityAndTwoKeywords(Integer.parseInt(intensity), kw1, kw2);
            if (coffees.isEmpty()) {
                output = "No Coffees satisfied these conditions.";
            } else {
                output = coffees.toString();
            }

            System.out.println(output);
            return 1;
            
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#14:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }

    }

    public static int task15() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of stores (top K):");
        String k = myScanner.nextLine();
        while(!stringIsValidIntValue(k)) {
            System.out.println("Enter number of stores (top K) (Int Value Only):");
            k = myScanner.nextLine();
        }

        System.out.println("Enter number of months:");
        String months = myScanner.nextLine();
        while(!stringIsValidIntValue(months)) {
            System.out.println("Enter number of months (Int Value Only):");
            months = myScanner.nextLine();
        }

        StoreDao storeDao = new StoreDao();
        try {
            List<Integer> storeNumbers = storeDao.getTopKStoresByHighestRevenueInXMonths(Integer.parseInt(k), Integer.parseInt(months));
            System.out.println(storeNumbers.toString());
            return 1;
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#15:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

    public static int task16() {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of customers (top K):");
        String k = myScanner.nextLine();
        while(!stringIsValidIntValue(k)) {
            System.out.println("Enter number of customers (top K) (Int Value Only):");
            k = myScanner.nextLine();
        }

        System.out.println("Enter number of months:");
        String months = myScanner.nextLine();
        while(!stringIsValidIntValue(months)) {
            System.out.println("Enter number of months (Int Value Only):");
            months = myScanner.nextLine();
        }

        CustomerDao customerDao = new CustomerDao();
        try {
            List<Integer> customerIds = customerDao.getTopKCustomersByHighestPurchasedSumInXMonths(Integer.parseInt(k), Integer.parseInt(months));
            System.out.println(customerIds.toString());
            return 1;
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#15:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }
}
