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
    Price float NOT NULL CHECK (Price > 0),
    Redeem_Points float NOT NULL CHECK (Price > 0),
    Reward_Points float NOT NULL CHECK (Price > 0),
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

DROP DOMAIN IF EXISTS Loyalty_Level_Enum CASCADE;
CREATE DOMAIN Loyalty_Level_Enum AS varchar(10)
CONSTRAINT loyalty_level_enum_value CHECK (VALUE in ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond'));

DROP DOMAIN IF EXISTS Month_Enum CASCADE;
CREATE DOMAIN Month_Enum AS char(3)
CONSTRAINT month_enum_value CHECK (VALUE in ('jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'));

-- Assumptions:
---- customers require specified first_name, last_name, birth_month, birth_day, phone_number, phone_type values
---- it is optional for a customer to specify their middle name
---- it is optional for a customer to be a member of the loyalty program (current_points/career_points/loyalty_level may be null)
---- phone_number must be unique
DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
    Customer_Id int NOT NULL,
    First_Name varchar(50) NOT NULL,
    Last_Name varchar(50) NOT NULL,
    Mid_Name varchar(50),
    Birth_Month Month_Enum NOT NULL,
    Birth_Day char(2) NOT NULL,
    Phone_Number varchar(16) NOT NULL,
    Phone_Type Phone_Enum NOT NULL,
    Current_Points float,
    Career_Points float,
    Loyalty_Level Loyalty_Level_Enum,

    CONSTRAINT PK_CUSTOMER PRIMARY KEY (Customer_Id),
    CONSTRAINT UQ_PHONE UNIQUE (Phone_Number)
);

-- Assumptions:
---- sales are associated with a specific customer (require a customer_id)
---- sales require specified purchased_portion and redeemed_portion values
---- if not specified, the default purchased_time is the current time
DROP TABLE IF EXISTS SALE CASCADE;
CREATE TABLE SALE (
  Purchase_Id int NOT NULL,
  Customer_Id int NOT NULL,
  Purchased_Time time DEFAULT CURRENT_TIME,
  Purchased_Portion float NOT NULL,
  Redeemed_Portion float NOT NULL,

  CONSTRAINT PK_SALE PRIMARY KEY (Purchase_Id),
  CONSTRAINT FK_CUSTOMER FOREIGN KEY (Customer_Id) REFERENCES CUSTOMER(Customer_Id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

-- Assumptions:
---- promotions require specified promo_name, start_date, end_date values
---- promo_name must be unique
DROP TABLE IF EXISTS PROMOTION cascade;
CREATE TABLE PROMOTION(
    Promo_Number int NOT NULL,
    Promo_Name varchar(50) NOT NULL,
    Start_Date date NOT NULL,
    End_date date NOT NULL,
    CONSTRAINT P_PK PRIMARY KEY (Promo_Number),
    CONSTRAINT UQ_Pname UNIQUE (Promo_Name)
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