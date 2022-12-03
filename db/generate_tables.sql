---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
---------------------------------------------

-- System Clock
-- Assumptions:
---- contains only one tuple, which is inserted as part of initialization
---- contains a "pseudo" date (p_date) that is modifiable for debugging/testing
---- p_date can not be null
DROP TABLE IF EXISTS CLOCK cascade;
CREATE TABLE CLOCK(
    p_date date NOT NULL
);


-- Assumptions:
---- coffees require specified coffee_name, price, redeem_points, and reward_points values
---- price, redeem_points, and reward_points must be positive values
---- coffee may have no description, country
---- coffee_name must be unique
---- intensity value must be not be greater than 12
DROP TABLE IF EXISTS COFFEE cascade;
CREATE TABLE COFFEE(
    Coffee_Id serial NOT NULL,
    Coffee_Name varchar(50) NOT NULL,
    Description VARCHAR(250),
    Country varchar(60),
    Intensity int NOT NULL CHECK (Intensity >= 1 AND Intensity <= 12),
    Price float NOT NULL CHECK (Price >= 0),
    Redeem_Points float NOT NULL CHECK (Redeem_Points >= 0),
    Reward_Points float NOT NULL CHECK (Reward_Points >= 0),
    CONSTRAINT C_PK PRIMARY KEY (Coffee_Id),
    CONSTRAINT UQ_Cname UNIQUE (Coffee_Name)
);

DROP DOMAIN IF EXISTS store_type CASCADE;
CREATE DOMAIN store_type varchar(7)
CONSTRAINT store_type_value CHECK(VALUE IN('kiosk', 'sitting'));

-- Assumptions:
---- stores require specified store_name, coordinates (lat/lon), store_type values
---- store_name must be unique
---- store_number is an auto-incremented integer value
DROP TABLE IF EXISTS STORE cascade;
CREATE TABLE STORE(
    Store_Number serial NOT NULL,
    Store_Name varchar(50) NOT NULL,
    Longitude float NOT NULL,
    Latitude float NOT NULL,
    Store_Type store_type NOT NULL,
    CONSTRAINT S_PK PRIMARY KEY (Store_Number),
    CONSTRAINT UQ_Sname UNIQUE (Store_Name)

);

DROP DOMAIN IF EXISTS Loyalty_Level_Enum CASCADE;
CREATE DOMAIN Loyalty_Level_Enum AS varchar(10)
CONSTRAINT loyalty_level_enum_value CHECK (VALUE in ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond', 'test'));
-- Assumptions:
---- Total_Points_Value_Unlocked_At must be positive value and cannot be NULL
---- Booster_Value must be a float in the range 1.0 - 4.0, and cannot be NULL
DROP TABLE IF EXISTS LOYALTY_PROGRAM CASCADE;
CREATE TABLE LOYALTY_PROGRAM (
    Loyalty_Level Loyalty_Level_Enum NOT NULL,
    Total_Points_Value_Unlocked_At float CHECK (Total_Points_Value_Unlocked_At >= 0) NOT NULL,
    Booster_Value float CHECK (Booster_Value >= 1 AND Booster_Value <= 4) NOT NULL,

    CONSTRAINT LOYALTY_PROGRAM_PK PRIMARY KEY (Loyalty_Level)
);

DROP DOMAIN IF EXISTS Phone_Enum CASCADE;
CREATE DOMAIN Phone_Enum AS varchar(6)
CONSTRAINT phone_enum_value CHECK (VALUE in ('home', 'mobile', 'work', 'other'));

DROP DOMAIN IF EXISTS Month_Enum CASCADE;
CREATE DOMAIN Month_Enum AS char(3)
CONSTRAINT month_enum_value CHECK (VALUE in ('jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'));

-- Assumptions:
---- customers require specified first_name, last_name, birth_month, birth_day, phone_number, phone_type values
---- it is optional for a customer to specify their middle initial
---- phone_number must be unique
---- it is optional for a customer to be a member of the loyalty program, and by default is NULL.
---- Current_Points and Total_Points must be positive values, and by default are 0.
DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
    Customer_Id int NOT NULL,
    First_Name varchar(50) NOT NULL,
    Last_Name varchar(50) NOT NULL,
    Mid_Initial char(1),
    Birth_Month Month_Enum NOT NULL,
    Birth_Day char(2) NOT NULL,
    Phone_Number varchar(16) NOT NULL,
    Phone_Type Phone_Enum NOT NULL,
    Loyalty_Level Loyalty_Level_Enum DEFAULT NULL,
    Current_Points float CHECK (Current_Points >= 0) DEFAULT NULL,
    Total_Points float CHECK (Total_Points >= 0) DEFAULT NULL,

    CONSTRAINT PK_CUSTOMER PRIMARY KEY (Customer_Id),
    CONSTRAINT UQ_PHONE UNIQUE (Phone_Number),
    CONSTRAINT FK_LOYALTY_PROGRAM FOREIGN KEY (Loyalty_Level) REFERENCES LOYALTY_PROGRAM(Loyalty_Level)
);

-- Assumptions:
---- sales are associated with a specific customer (require a customer_id)
---- purchased_portion and redeemed_portion must be positive values.
---- sales require specified purchased_portion and redeemed_portion values
---- if not specified, the default purchased_time is the current time
DROP TABLE IF EXISTS SALE CASCADE;
CREATE TABLE SALE (
  Purchase_Id int NOT NULL,
  Customer_Id int NOT NULL,
  Purchased_Time time DEFAULT CURRENT_TIME,
  Purchased_Portion float NOT NULL CHECK (Purchased_Portion >= 0),
  Redeemed_Portion float NOT NULL CHECK (Redeemed_Portion >= 0),

  CONSTRAINT PK_SALE PRIMARY KEY (Purchase_Id),
  CONSTRAINT FK_CUSTOMER FOREIGN KEY (Customer_Id) REFERENCES CUSTOMER(Customer_Id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

-- Assumptions:
---- promotions require specified promo_name, start_date, end_date values
---- promo_name must be unique
---- start_date must occur before end_date
DROP TABLE IF EXISTS PROMOTION cascade;
CREATE TABLE PROMOTION(
    Promo_Number serial NOT NULL,
    Promo_Name varchar(50) NOT NULL,
    Start_Date date NOT NULL,
    End_Date date NOT NULL,
    CONSTRAINT P_PK PRIMARY KEY (Promo_Number),
    CONSTRAINT UQ_Pname UNIQUE (Promo_Name),
    CONSTRAINT Check_Valid_Dates CHECK (Start_Date < End_Date)
);

DROP TABLE IF EXISTS INCLUDES cascade;
CREATE TABLE INCLUDES(
    Promo_Number int NOT NULL,
    Coffee_Id int NOT NULL,
    CONSTRAINT PK_INCLUDES PRIMARY KEY (Promo_Number, Coffee_Id),
    CONSTRAINT FK1_INCLUDES FOREIGN KEY (Coffee_Id) REFERENCES COFFEE (Coffee_Id)
                     ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS OFFERS cascade;
CREATE TABLE OFFERS(
    Promo_Number int NOT NULL,
    Store_Number int NOT NULL,
    CONSTRAINT PK_OFFERS PRIMARY KEY (Promo_Number, Store_Number),
    CONSTRAINT FK1_OFFERS FOREIGN KEY (Store_Number) REFERENCES STORE (Store_Number)
                  ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS FEATURES cascade;
CREATE TABLE FEATURES(
    Coffee_Id int NOT NULL,
    Store_Number int NOT NULL,
    CONSTRAINT PK_FEATURES PRIMARY KEY (Coffee_Id, Store_Number),
    CONSTRAINT FK1_FEATURES FOREIGN KEY (Store_Number) REFERENCES STORE (Store_Number)
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK2_FEATURES FOREIGN KEY (Coffee_Id) REFERENCES COFFEE (Coffee_Id)
                  ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS RECORDS CASCADE;
CREATE TABLE RECORDS(
    Purchase_Id int NOT NULL,
    Store_Number int NOT NULL,
    Coffee_Id int NOT NULL,

    CONSTRAINT RECORDS_PK PRIMARY KEY (Purchase_Id, Store_Number, Coffee_Id),
    CONSTRAINT SALE_FK FOREIGN KEY (Purchase_Id) REFERENCES SALE(Purchase_Id)
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT STORE_FK FOREIGN KEY (Store_Number) REFERENCES STORE(Store_Number)
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT COFFEE_FK FOREIGN KEY (Coffee_Id) REFERENCES COFFEE(Coffee_Id)
                  ON UPDATE CASCADE ON DELETE CASCADE
);

-- Trigger Assumptions:
---- Trigger 1:
----- When a customer makes a Sale, if the Birth_Month and Birth_Day of the specified Customer_Id
----- matches the Sale's date, the final reward points earned for purchasing a coffee will be multiplied by 1.10 (10%).
---- Trigger 2:
----- For different Loyalty_Level of Loyalty_Program, there’s a
----- different Booster_value that is multiplied by the Reward_Points
----- for the final reward points earned for purchasing a Coffee.
---- Trigger 3:
----- The final reward points value calculated for a sale will be
----- added to the Customer's Current_Points and Total_Points.
---- Trigger 4:
----- Customer’s Loyalty_Level will get updated if their Total_Points
----- increases to a certain level (Total_Points_Value_Unlocked_At).
---- Trigger 5:
----- A Promotion (by Promotion_Id) will be removed whenever it's end_date
----- is equal to the current date.
---- Trigger 6:
----- If the Customer's Current_Points is >= the Coffee's Redeem_Points, they may choose
----- to acquire the Coffee for free in their Sale. When a Coffee is acquired this way, the Coffee's Redeem_Points
----- value amount is subtracted from the Customer's Current_Points.
