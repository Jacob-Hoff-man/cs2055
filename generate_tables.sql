---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
---------------------------------------------
DROP TABLE IF EXISTS CUSTOMER CASCADE;
DROP TABLE IF EXISTS SALE CASCADE;

DROP DOMAIN IF EXISTS Phone_Enum CASCADE;
DROP DOMAIN IF EXISTS Loyalty_Level_Enum CASCADE;

CREATE DOMAIN Phone_Enum AS varchar(6)
CHECK (VALUE in ('home', 'mobile', 'work', 'other'));

CREATE DOMAIN Loyalty_Level_Enum AS varchar(10)
CHECK (VALUE in ('basic', 'bronze', 'silver', 'gold', 'platinum', 'diamond'));

CREATE TABLE CUSTOMER (
    Customer_Id integer,
    First_Name varchar(50),
    Last_Name varchar(50),
    Mid_Name varchar(50),
    Birth_Month char(3),
    Birth_Day char(2),
    Phone_Number varchar(16),
    Phone_Type Phone_Enum,
    Current_Points float,
    Career_Points float,
    Loyalty_Level Loyalty_Level_Enum,

    CONSTRAINT PK_CUSTOMER PRIMARY KEY (Customer_Id),
    CONSTRAINT UQ_PHONE UNIQUE (Phone_Number)
);

CREATE TABLE SALE (
  Purchase_Id integer,
  Purchased_Time time,
  Purchased_Portion float,
  Redeemed_Portion float,

  CONSTRAINT PK_SALE PRIMARY KEY (Purchase_Id)
);

DROP DOMAIN IF EXISTS store_type CASCADE;
CREATE DOMAIN store_type varchar(7)
CONSTRAINT store_type_value CHECK(VALUE IN('kiosk', 'sitting store'));


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

DROP TABLE IF EXISTS PROMOTION cascade;
CREATE TABLE PROMOTION(
    Promo_Number int NOT NULL,
    Promo_Name varchar(50) NOT NULL,
    Start_Date date NOT NULL,
    End_date date NOT NULL,
    CONSTRAINT P_PK PRIMARY KEY (Promo_Number),
    CONSTRAINT UQ_Pname UNIQUE (Promo_Name)

);

DROP TABLE IF EXISTS COFFEE cascade;
CREATE TABLE COFFEE(
    Coffee_Id int NOT NULL,
    Coffee_Name varchar(50),
    Description VARCHAR(250),
    Country varchar(60),
    Intensity int NOT NULL CHECK (Intensity <= 12),
    Price float NOT NULL,
    Redeem_Points float NOT NULL,
    Reward_Points float NOT NULL,
    CONSTRAINT C_PK PRIMARY KEY (Coffee_Id),
    CONSTRAINT UQ_Cname UNIQUE (Coffee_Name)

)
