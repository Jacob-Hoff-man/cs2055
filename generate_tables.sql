---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
---------------------------------------------
DROP TABLE IF EXISTS CUSTOMER CASCADE;

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