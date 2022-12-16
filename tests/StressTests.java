package tests;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import db.TasksDriver;
import models.Coffee;
import models.LoyaltyProgram;
import models.Promotion;
import models.Store;
import models.Record;
import models.Sale;

public class StressTests {
    public static int increment = 100000; 
    public static long task1() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < increment; i++) {
            Store myStore = new Store();
            myStore.setStoreName(String.valueOf(i));
            myStore.setLongitude(0);
            myStore.setLatitude(0);
            myStore.setStoreType("sitting");
            TasksDriver.task1(myStore);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task2() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            Coffee coffee = new Coffee();
            coffee.setCoffeeName(String.valueOf(i));
            coffee.setDescription("");
            coffee.setCountry("");
            coffee.setIntensity(4);
            coffee.setPrice(1);
            coffee.setRedeemPoints(1);
            coffee.setRewardPoints(1);

            TasksDriver.task2(coffee);
        }
    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }


    public static long task3() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            Promotion promotion = new Promotion();
            promotion.setPromoName(String.valueOf(i));
            promotion.setStartDate(Date.valueOf("2022-10-01"));
            promotion.setEndDate(Date.valueOf("2022-10-02"));

            TasksDriver.task3(promotion, 1);
        }

    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }
    
    // public static long task4() {
    //     long startTime = System.currentTimeMillis();
    // need to add many stores, promos first  
    //     for (int i = 0; i < increment; i++) {
    //         TasksDriver.task4(i, i)
    //     }

    
    //     long endTime = System.currentTimeMillis();
    //     System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
    //     return endTime - startTime;
    // }

    public static long task5NoCoffeeId() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task5("");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task5() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task5(String.valueOf(1));
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task6NoCoffeeId() {
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task6(1, "");
        }

    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task6() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task6(1, String.valueOf(1));
        }

    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task7NoPromotionId() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task7(0, 0, "");
        }
    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task7() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task7(0, 0, String.valueOf(1));
        }
    
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task8() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
            loyaltyProgram.setLoyaltyLevel("test");
            loyaltyProgram.setTotalPointsValueUnlockedAt((float)100);
            loyaltyProgram.setBoosterValue((float)4);
            TasksDriver.task8(loyaltyProgram);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    // public static long task9() {
    //     long startTime = System.currentTimeMillis();
    // // need to auto increment the telephone number somehow.... maybe with 000000000000 <- value, and then replace index 3, 7 with "-"
    //     for (int i = 0; i < increment; i++) {
    //         Customer customer = new Customer();
    //         customer.setFirstName(firstName);
    //         customer.setLastName(lastName);
    //         customer.setMidInitial(midInitial);
    //         customer.setBirthDay(birthDay);
    //         customer.setBirthMonth(birthMonth);
    //         customer.setPhoneNumber(phoneNumber);
    //         customer.setPhoneType(phoneType);

    //         TasksDriver.task9(
    //     }

    //     long endTime = System.currentTimeMillis();
    //     System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
    //     return endTime - startTime;
    // }

    public static long task10() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task10(1);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task11() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task11();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + " increments!");
        return endTime - startTime;
    }

    public static long task12() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
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
        sale.setRecords(records);

        for (int i = 0; i < increment; i++) {
            TasksDriver.task12(sale);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
        return endTime - startTime;
    }

    public static long task13() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task13();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
        return endTime - startTime;
    }

    public static long task14() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task14(6, "es", "ess");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
        return endTime - startTime;
    }

    public static long task15() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task15(3, 15);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
        return endTime - startTime;
    }

    public static long task16() {
        DBConnection.restartDatabaseState();
        long startTime = System.currentTimeMillis();
    
        for (int i = 0; i < increment; i++) {
            TasksDriver.task16(3, 15);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds over " + increment + "increments!");
        return endTime - startTime;
    }
}
