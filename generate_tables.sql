---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
---------------------------------------------
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