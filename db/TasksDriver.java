package db;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exceptions.CoffeeByIdDoesNotExistException;
import exceptions.PromotionByIdDoesNotExistException;
import exceptions.StoreByIdDoesNotExistException;
import models.Coffee;
import models.Promotion;
import models.Store;
import services.Coffee.CoffeeDao;
import services.Promotion.PromotionDao;
import services.Store.StoreDao;

public class TasksDriver {
    protected static final String intRegex = "^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$";
    protected static final String floatRegex = "[-+]?[0-9]*\\.?[0-9]+";
    protected static final String[] storeTypes = {"kiosk", "sitting"};

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
            // verify the promotion exists
            Promotion promotion = promotionDao.getPromotion(Integer.parseInt(promoNumber));
            if (promotion == null) throw new PromotionByIdDoesNotExistException();
            // verify the store exists
            Store store = storeDao.getStore(Integer.parseInt(storeNumber));
            if (store == null) throw new StoreByIdDoesNotExistException();
            
            return promotionDao.updatePromotionWithOfferedStore(promotion, store.getStoreNumber());

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

        PromotionDao promotionDao = new PromotionDao();
        StoreDao storeDao = new StoreDao();
        try {
            String output;
            if (coffeeId.isBlank()) {
                List<Store> promoStores = storeDao.getStoresWithPromotions();
                output = promoStores.isEmpty() ? 
                    "No stores are currently offering any promotions." : 
                    promoStores.toString();
            } else {
                promotionDao.deleteOffers(3, 2);
                List<Store> promoStores = storeDao.getStoresWithPromotionsByCoffeeId(Integer.parseInt(coffeeId));
                output = promoStores.isEmpty() ?
                    "No stores are currently promoting this coffee." :
                    promoStores.toString();
            }

            System.out.println(output);
            return 1;
            
        } catch (SQLException e) {
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

        return 0;
        
    }
}
