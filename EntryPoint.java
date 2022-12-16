import java.util.Scanner;

import db.TasksDriver;
import tests.StressTests;
import tests.TasksTests;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter 0 for Testing, or 1 for Task Runner:");
        String input = myScanner.nextLine();

        if (Integer.parseInt(input) == 0) {
            runAllUnitTests();

            runAllStressTests(100);
            runAllStressTests(1000);
            runAllStressTests(10000);
        } else {
            // run task runner
            System.out.println("\n------------------\nOPERATION OUTPUT: " + TasksDriver.taskRunner());
        }
    }

    public static void runAllStressTests(int currentIncrement) {
        StressTests.increment = currentIncrement;
        // run all tests
        System.out.println("RUNNING STRESS TESTS FOR " + currentIncrement + "INCREMENTS");
        System.out.println("TASK 1 STRESS TEST");
        StressTests.task1();
        System.out.println("TASK 2 STRESS TEST");
        StressTests.task2();
        System.out.println("TASK 3 STRESS TEST");
        StressTests.task3();
        // System.out.println("TASK 4 STRESS TEST");
        // StressTests.task4();
        System.out.println("TASK 5 (NO COFFEE ID) STRESS TEST");
        StressTests.task5NoCoffeeId();
        System.out.println("TASK 5 STRESS TEST");
        StressTests.task5();
        System.out.println("TASK 6 STRESS TEST");
        StressTests.task6();
        System.out.println("TASK 6 (NO COFFEE ID) STRESS TEST");
        StressTests.task6NoCoffeeId();
        System.out.println("TASK 7 STRESS TEST");
        StressTests.task7();
        System.out.println("TASK 7 (NO PROMOTION ID) STRESS TEST");
        StressTests.task7();
        System.out.println("TASK 8 STRESS TEST");
        StressTests.task8();
        // System.out.println("TASK 9 STRESS TEST");
        // StressTests.task9();
        System.out.println("TASK 10 STRESS TEST");
        StressTests.task10();
        System.out.println("TASK 11 STRESS TEST");
        StressTests.task11();
        System.out.println("TASK 12 STRESS TEST");
        StressTests.task12();
        System.out.println("TASK 13 STRESS TEST");
        StressTests.task13();
        System.out.println("TASK 14 STRESS TEST");
        StressTests.task14();
        System.out.println("TASK 15 STRESS TEST");
        StressTests.task15();
        System.out.println("TASK 16 STRESS TEST");
        StressTests.task16();
    }
    public static void runAllUnitTests() {
        // run all tests
        System.out.println("RUNNING TRIGGER UNIT TESTS");
        System.out.println("TRIGGER 1 TEST CASE 1: CUSTOMER'S CURRENT/TOTAL POINTS ARE UPDATED AFTER INSERTING SALE");
        System.out.println("PASSED = " + TasksTests.trigger1TestCase1());
        System.out.println("TRIGGER 1 TEST CASE 2: CUSTOMER'S CURRENT/TOTAL POINTS ARE FACTORED DIFFERENTLY BASED ON CURRENT LOYALTY PROGRAM");
        System.out.println("PASSED = " + TasksTests.trigger1TestCase2());
        System.out.println("TRIGGER 1 TEST CASE 3: CUSTOMER'S CURRENT/TOTAL POINTS ARE FACTORED DIFFERENTLY BASED ON IF CUSTOMER'S BIRTH DAY");
        System.out.println("PASSED = " + TasksTests.trigger1TestCase3());
        System.out.println("TRIGGER 2 TEST CASE 1: CUSTOMER'S LOYALTY PROGRAM IS UPDATED AFTER ACCUMULATING A CERTAIN TOTAL REWARD POINTS");
        System.out.println("PASSED = " + TasksTests.trigger2TestCase1());
        System.out.println("TRIGGER 3 TEST CASE 1: PROMOTION IS REMOVED WHEN IT'S END DATE IS <= P_CLOCK CURRENT DATE");
        System.out.println("PASSED = " + TasksTests.trigger3TestCase1());

        System.out.println("RUNNING TASK UNIT TESTS");
        System.out.println("TASK 1 TEST CASE 1: PRODUCE ERROR IF STORE WITH SAME NAME ALREADY EXISTS");
        System.out.println("PASSED = " + TasksTests.task1TestCase1());
        System.out.println("TASK 1 TEST CASE 2: INSERT A NEW STORE INTO THE DB AND ASSIGN A UNIQUE STORE NUMBER");
        System.out.println("PASSED = " + TasksTests.task1TestCase2());
        System.out.println("TASK 1 TEST CASE 3: VERIFY THE NEW STORE NUMBER IS ASSOCIATED WITH THE NEW STORE");
        System.out.println("PASSED = " + TasksTests.task1TestCase3());

        System.out.println("TASK 2 TEST CASE 1: INSERT A NEW COFFEE INTO THE DB AND ASSIGN A UNIQUE COFFEE ID");
        System.out.println("PASSED = " + TasksTests.task2TestCase1());
        System.out.println("TASK 2 TEST CASE 2: VERIFY THE NEW COFFEE ID IS ASSOCIATED WITH THE NEW COFFEE");
        System.out.println("PASSED = " + TasksTests.task2TestCase2());
        
        System.out.println("TASK 3 TEST CASE 1: INSERT A NEW PROMOTION INTO THE DB AND ASSIGN A UNIQUE PROMOTION ID");
        System.out.println("PASSED = " + TasksTests.task3TestCase1());
        System.out.println("TASK 3 TEST CASE 2: VERIFY THE NEW PROMOTION ID IS ASSOCIATED WITH THE NEW PROMOTION");
        System.out.println("PASSED = " + TasksTests.task3TestCase2());

        System.out.println("TASK 4 TEST CASE 1: INSERT A NEW OFFERS RELATIONAL ENTITY");
        System.out.println("PASSED = " + TasksTests.task4TestCase1());
        System.out.println("TASK 4 TEST CASE 2: VERIFY THE NEW OFFERS STORE NUMBER IS ASSOCIATED WITH A VALID STORE");
        System.out.println("PASSED = " + TasksTests.task4TestCase2());

        System.out.println("TASK 5 TEST CASE 1: LIST THE CORRECT STORES WITH PROMOTIONS");
        System.out.println("PASSED = " + TasksTests.task5TestCase1());
        System.out.println("TASK 5 TEST CASE 2: LIST IS EMPTY WHEN NO STORES WITH PROMOTIONS");
        System.out.println("PASSED = " + TasksTests.task5TestCase2());
        System.out.println("TASK 5 TEST CASE 3: LIST THE CORRECT STORES WITH PROMOTIONS FOR A SPECIFIC COFFEE");
        System.out.println("PASSED = " + TasksTests.task5TestCase3());
        System.out.println("TASK 5 TEST CASE 4: LIST IS EMPTY WHEN NO STORES WITH PROMOTIONS FOR A SPECIFIC COFFEE");
        System.out.println("PASSED = " + TasksTests.task5TestCase4());

        
        System.out.println("TASK 6 TEST CASE 1: LIST THE CORRECT PROMOTIONS OFFERED BY A SPECIFIC STORE");
        System.out.println("PASSED = " + TasksTests.task6TestCase1());
        System.out.println("TASK 6 TEST CASE 2: LIST IS EMPTY WHEN NO PROMOTIONS OFFERED BY A SPECIFIC STORE");
        System.out.println("PASSED = " + TasksTests.task6TestCase2());
        System.out.println("TASK 6 TEST CASE 3: LIST THE CORRECT PROMOTIONS OFFERED BY A SPECIFIC STORE FOR A SPECIFIC COFFEE");
        System.out.println("PASSED = " + TasksTests.task6TestCase3());
        System.out.println("TASK 6 TEST CASE 4: LIST IS EMPTY WHEN NO PROMOTIONS OFFERED BY A SPECIFIC STORE FOR A SPECIFIC COFFEE");
        System.out.println("PASSED = " + TasksTests.task6TestCase4());
        
        System.out.println("TASK 7 TEST CASE 1: LIST THE CLOSEST STORES");
        System.out.println("PASSED = " + TasksTests.task7TestCase1());
        System.out.println("TASK 7 TEST CASE 2: LIST IS EMPTY WHEN NO STORES ARE AVAILABLE");
        System.out.println("PASSED = " + TasksTests.task7TestCase2());
        System.out.println("TASK 7 TEST CASE 3 LIST THE CLOSEST STORES OFFERING A SPECIFIC PROMOTION:");
        System.out.println("PASSED = " + TasksTests.task7TestCase3());
        System.out.println("TASK 7 TEST CASE 4: LIST IS EMPTY WHEN NO STORES ARE AVAILABLE OFFERING A SPECIFIC PROMOTION");
        System.out.println("PASSED = " + TasksTests.task7TestCase4());
        System.out.println("TASK 7 TEST CASE 5: LIST THE CLOSEST STORES, INCLUDING ALL EQUIDISTANTLY TIED");
        System.out.println("PASSED = " + TasksTests.task7TestCase5());
        System.out.println("TASK 7 TEST CASE 6: LIST THE CLOSEST STORES OFFERING A SPECIFIC PROMOTION, INCLUDING ALL EQUIDISTANTLY TIED");
        System.out.println("PASSED = " + TasksTests.task7TestCase6());

        System.out.println("TASK 8 TEST CASE 1: INSERT A NEW LOYALTY PROGRAM IF A RECORD WITH LOYALTY LEVEL KEY DOES NOT EXIST");
        System.out.println("PASSED = " + TasksTests.task8TestCase1());
        System.out.println("TASK 8 TEST CASE 2: UPDATE LOYALTY PROGRAM IF A RECORD WITH LOYALTY LEVEL KEY EXISTS");
        System.out.println("PASSED = " + TasksTests.task8TestCase2());
        System.out.println("TASK 8 TEST CASE 3: PRODUCE ERROR IF LOYALTY LEVEL IS NOT VALID ENTRY");
        System.out.println("PASSED = " + TasksTests.task8TestCase3());
        System.out.println("TASK 8 TEST CASE 4: PRODUCE ERROR IF NEW LOYALTY PROGRAM'S TOTAL POINTS VALUE UNLOCKED AT IS <= 0");
        System.out.println("PASSED = " + TasksTests.task8TestCase4());
        System.out.println("TASK 8 TEST CASE 5: PRODUCE ERROR IF NEW LOYALTY PROGRAM'S BOOSTER VALUE IS < 1");
        System.out.println("PASSED = " + TasksTests.task8TestCase5());
        System.out.println("TASK 8 TEST CASE 6: PRODUCE ERROR IF NEW LOYALTY PROGRAM'S BOOSTER VALUE IS > 4");
        System.out.println("PASSED = " + TasksTests.task8TestCase6());

        System.out.println("TASK 9 TEST CASE 1: INSERT A NEW CUSTOMER INTO THE DB AND ASSIGN A UNIQUE CUSTOMER ID");
        System.out.println("PASSED = " + TasksTests.task9TestCase1());
        System.out.println("TASK 9 TEST CASE 2: SET LOYALTY LEVEL TO BASIC BY DEFAULT WHEN INSERTING A NEW CUSTOMER");
        System.out.println("PASSED = " + TasksTests.task9TestCase2());

        System.out.println("TASK 10 TEST CASE 1: GET THE CORRECT CURRENT POINTS FOR A CUSTOMER");
        System.out.println("PASSED = " + TasksTests.task10TestCase1());
        System.out.println("TASK 10 TEST CASE 2: GET THE CORRECT TOTAL POINTS FOR A CUSTOMER");
        System.out.println("PASSED = " + TasksTests.task10TestCase2());

        System.out.println("TASK 11 TEST CASE 1: LIST THE RANKED MOST VALUABLE CUSTOMERS");
        System.out.println("PASSED = " + TasksTests.task11TestCase1());
        
        System.out.println("TASK 12 TEST CASE 1: INSERT A NEW SALE INTO THE DB AND ASSIGN A UNIQUE PURCHASE ID");
        System.out.println("PASSED = " + TasksTests.task12TestCase1());
        System.out.println("TASK 12 TEST CASE 2: PRODUCE ERROR IF NEW SALE'S CUSTOMER HAS AN INSUFFICIENT POINTS BALANCE");
        System.out.println("PASSED = " + TasksTests.task12TestCase2());

        System.out.println("TASK 13 TEST CASE 1: LIST THE COFFEES IN THE DB");
        System.out.println("PASSED = " + TasksTests.task13TestCase1());
        System.out.println("TASK 13 TEST CASE 2: LIST IS EMPTY WHEN THERE ARE NO COFFEES AVAILABLE");
        System.out.println("PASSED = " + TasksTests.task13TestCase2());

        System.out.println("TASK 14 TEST CASE 1: LIST THE COFFEE NAMES MATCHING A SPECIFIED INTENSITY AND TWO KEYWORDS");
        System.out.println("PASSED = " + TasksTests.task14TestCase1());
        System.out.println("TASK 14 TEST CASE 2: LIST IS EMPTY WHEN THERE ARE NO COFFEES MATCHING A SPECIFIED INTENSITY AND TWO KEYWORDS");
        System.out.println("PASSED = " + TasksTests.task14TestCase2());

        System.out.println("TASK 15 TEST CASE 1: LIST THE TOP 3 STORE NUMBERS HAVING THE HIGHEST REVENUE FOR THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task15TestCase1());
        System.out.println("TASK 15 TEST CASE 2: LIST THE TOP 1 STORE NUMBERS HAVING THE HIGHEST REVENUE FOR THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task15TestCase2());
        System.out.println("TASK 15 TEST CASE 3: LIST THE TOP 3 STORE NUMBERS HAVING THE HIGHEST REVENUE FOR THE LAST 6 MONTHS");
        System.out.println("PASSED = " + TasksTests.task15TestCase3());
        System.out.println("TASK 15 TEST CASE 4: LIST THE TOP 2 (BUT 3 ARE RETURNED BECAUSE OF TIE) STORE NUMBERS HAVING THE HIGHEST REENUE FOR THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task15TestCase4());
        System.out.println("TASK 15 TEST CASE 5: LIST IS EMPTY WHEN THERE ARE NO STORES MATCHING TO RANK BY REVENUE IN THE GIVEN TIME RANGE");
        System.out.println("PASSED = " + TasksTests.task15TestCase5());

        System.out.println("TASK 16 TEST CASE 1: LIST THE TOP 3 CUSTOMERS IDS HAVING SPENT THE MOST IN THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task16TestCase1());
        System.out.println("TASK 16 TEST CASE 2: LIST THE TOP 1 CUSTOMERS HAVING SPENT THE MOST IN THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task16TestCase2());
        System.out.println("TASK 16 TEST CASE 3: LIST THE TOP 2 CUSTOMERS HAVING SPENT THE MOST IN THE LAST 6 MONTHS");
        System.out.println("PASSED = " + TasksTests.task16TestCase3());
        System.out.println("TASK 16 TEST CASE 4: LIST THE TOP 2 (BUT 3 ARE RETURNED BECAUSE OF TIE) CUSTOMER IDS HAVING SPENT THE MOST IN THE LAST 15 MONTHS");
        System.out.println("PASSED = " + TasksTests.task16TestCase4());
        System.out.println("TASK 16 TEST CASE 5: LIST IS EMPTY WHEN THERE ARE NO CUSTOMERS MATCHING TO RANK BY SPENDING IN THE GIVEN TIME RANGE");
        System.out.println("PASSED = " + TasksTests.task16TestCase5());
    }

}