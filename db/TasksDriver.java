package db;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import models.Store;
import services.StoreDao;

public class TasksDriver {
    public static String floatRegex = "[-+]?[0-9]*\\.?[0-9]+";
    public static String[] storeTypes = {"kiosk", "sitting"};

    //helpers
    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public static boolean stringIsValidFloatValue(String inputStr) {
        return inputStr.matches(floatRegex);
    }

    //tasks
    public static int Task1() {
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
            storeDao.addStore(myStore);
            return storeDao.getStore(myStore.getStoreName()).getStoreNumber();
            
        } catch (SQLException e) {
            System.out.println("An error occured while performing Task#1:");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getStackTrace());
            return -1;
        }
    }
}
