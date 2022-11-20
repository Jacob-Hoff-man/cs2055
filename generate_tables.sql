---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
---------------------------------------------

-- Assumptions:
---- coffees require specified coffee_name, price, redeem_points, and reward_points values
---- price, redeem_points, and reward_points must be positive values
---- coffee may have no description, country
---- coffee_name must be unique
---- intensity value must be not be greater than 12
DROP TABLE IF EXISTS COFFEE cascade;
CREATE TABLE COFFEE(
    Coffee_Id int NOT NULL,
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
DROP TABLE IF EXISTS STORE cascade;
CREATE TABLE STORE(
    Store_Number int NOT NULL,
    Store_Name varchar(50) NOT NULL,
    Longitude float NOT NULL,
    Latitude float NOT NULL,
    Store_Type store_type NOT NULL,
    CONSTRAINT S_PK PRIMARY KEY (Store_Number),
    CONSTRAINT UQ_Sname UNIQUE (Store_Name)

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

    CONSTRAINT PK_CUSTOMER PRIMARY KEY (Customer_Id),
    CONSTRAINT UQ_PHONE UNIQUE (Phone_Number)
);

DROP DOMAIN IF EXISTS Loyalty_Level_Enum CASCADE;
CREATE DOMAIN Loyalty_Level_Enum AS varchar(10)
CONSTRAINT loyalty_level_enum_value CHECK (VALUE in ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond'));

-- Assumptions:
---- it is optional for a customer to be a member of the loyalty program
---- Current_Points and Total_Points must be positive values
DROP TABLE IF EXISTS LOYALTY_PROGRAM CASCADE;
CREATE TABLE LOYALTY_PROGRAM (
    Customer_Id int NOT NULL,
    Current_Points float CHECK (Current_Points >= 0),
    Total_Points float CHECK (Total_Points >= 0),
    Loyalty_Level Loyalty_Level_Enum,

    CONSTRAINT LOYALTY_PROGRAM_PK PRIMARY KEY (Customer_Id),
    CONSTRAINT LOYALTY_PROGRAM_FK FOREIGN KEY (Customer_Id) REFERENCES Customer(Customer_Id)
        ON UPDATE CASCADE ON DELETE CASCADE
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
    Promo_Number int NOT NULL,
    Promo_Name varchar(50) NOT NULL,
    Start_Date date NOT NULL,
    End_Date date NOT NULL,
    CONSTRAINT P_PK PRIMARY KEY (Promo_Number),
    CONSTRAINT UQ_Pname UNIQUE (Promo_Name),
    CONSTRAINT Check_Valid_Dates CHECK (Start_Date < End_Date)
);

DROP TABLE IF EXISTS OFFERS cascade;
CREATE TABLE OFFERS(
    Promo_Number int NOT NULL,
    Coffee_Id int NOT NULL,
    Store_Number int NOT NULL,
    CONSTRAINT PK_OFFERS PRIMARY KEY (Promo_Number, Store_Number),
    CONSTRAINT FK1_OFFERS FOREIGN KEY (Store_Number) REFERENCES STORE (Store_Number)
                  ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK2_OFFERS FOREIGN KEY (Coffee_Id) REFERENCES COFFEE (Coffee_Id)
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
----- For each Customer_Id in Customer Entity, if the Birth_Month and Birth_Day
----- matches the purchase time, each customer will get 10% more points of their
----- Current_Points and get added to in their Loyalty_Program.
---- Trigger 2:
----- For different Loyalty_Level of Loyalty_Program, there’s a
----- different level of booster factors that can multiply to the Reward_Points.
---- Trigger 3:
----- Customer’s Loyalty_Level will get updated if their Career_Points
----- increase or decrease to certain level.
---- Trigger 4:
----- A Promotion (by Promotion_Id) will be removed whenever it's end_date reached.
