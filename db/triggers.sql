---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
-------------------------------------------
-- task 1
CREATE OR REPLACE PROCEDURE add_store(inp_store_name varchar(50), inp_longitude float, inp_latitude float, inp_store_type varchar(7))
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO STORE (store_name, longitude, latitude, store_type) 
    VALUES (inp_store_name, inp_longitude, inp_latitude, inp_store_type);
END;
$$;

CREATE OR REPLACE FUNCTION get_store_number(inp_store_name varchar(50))
RETURNS int
AS $$
DECLARE
    store_number int;
BEGIN
    SELECT * INTO store_number
    FROM STORE
    WHERE store_name = inp_store_name;

    RETURN store_number;
END;
$$ LANGUAGE plpgsql;

-- task 2
CREATE OR REPLACE PROCEDURE add_coffee(inp_coffee_name varchar(50), inp_description varchar(250), inp_country varchar(60), inp_intensity int, inp_price float, inp_redeem_points float, inp_reward_points float)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points)
    VALUES (inp_coffee_name, inp_description, inp_country, inp_intensity, inp_price, inp_redeem_points, inp_reward_points);
END;
$$;

CREATE OR REPLACE FUNCTION get_coffee_id(inp_coffee_name varchar(50))
RETURNS int
AS $$
DECLARE
    coffee_id int;
BEGIN
    SELECT * INTO coffee_id
    FROM COFFEE
    WHERE coffee_name = inp_coffee_name;

    RETURN coffee_id;
END;
$$ LANGUAGE plpgsql;

-- task 3
-- Assumptions:
---- A Coffee must exist in the database that corresponds to the inp_coffee_id in Includes relation entity
---- When a Promotion is entered into the db, a corresponding Includes relational entity will be subsequently inserted
CREATE OR REPLACE FUNCTION check_if_coffee_exists(inp_coffee_id int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM COFFEE
                    WHERE coffee_id = inp_coffee_id );
$$
LANGUAGE SQL;

ALTER TABLE INCLUDES
   ADD CONSTRAINT coffee_exists CHECK (check_if_coffee_exists(coffee_id));

CREATE OR REPLACE PROCEDURE add_promotion_with_included_coffee(inp_promo_name varchar(50), inp_start_date date, inp_end_date date, inp_coffee_id int)
LANGUAGE plpgsql
AS $$
DECLARE
    new_promo_number int;
BEGIN
    INSERT INTO PROMOTION (promo_name, start_date, end_date)
    VALUES (inp_promo_name, inp_start_date, inp_end_date);

    SELECT promo_number INTO new_promo_number FROM PROMOTION WHERE promo_name = inp_promo_name;

    INSERT INTO INCLUDES (promo_number, coffee_id)
    VALUES (new_promo_number, inp_coffee_id);
END
$$;

CREATE OR REPLACE FUNCTION get_promotion_number(inp_promo_name varchar(50))
RETURNS int
AS $$
DECLARE
    promo_number int;
BEGIN
    SELECT * INTO promo_number
    FROM PROMOTION
    WHERE promo_name = inp_promo_name;

    RETURN promo_number;
END;
$$ LANGUAGE plpgsql;

-- task 4
-- Assumptions:
---- A Promotion must exist in the database that corresponds to the inp_promo_number in Offers relation entity
---- A Store must exist in the database that corresponds to the inp_store_number in Offers relation entity
CREATE OR REPLACE FUNCTION check_if_promotion_exists(inp_promo_number int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM PROMOTION
                    WHERE promo_number = inp_promo_number );
$$
LANGUAGE SQL;

ALTER TABLE OFFERS
   ADD CONSTRAINT promotion_exists CHECK (check_if_promotion_exists(promo_number));

CREATE OR REPLACE FUNCTION check_if_store_exists(inp_store_number int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM STORE
                    WHERE store_number = inp_store_number );
$$
LANGUAGE SQL;

ALTER TABLE OFFERS
   ADD CONSTRAINT store_exists CHECK (check_if_store_exists(store_number));

CREATE OR REPLACE PROCEDURE add_promotion_offering_at_store(inp_promo_number int, inp_store_number int)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO OFFERS (promo_number, store_number)
    VALUES (inp_promo_number, inp_store_number);
END
$$;

-- Task 5
CREATE OR REPLACE FUNCTION get_stores_with_promotions()
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT * FROM STORE WHERE store_number IN (SELECT DISTINCT store_number FROM OFFERS);
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 6
CREATE OR REPLACE FUNCTION get_promotions_offered_by_store(inp_store_number int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
                 FROM PROMOTION WHERE promo_number IN
                    (SELECT DISTINCT promo_number
                           FROM OFFERS
                           WHERE store_number = inp_store_number
                    );
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_promotions_offered_by_store_by_coffee_id(inp_store_number int, inp_coffee_id int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
                 FROM PROMOTION WHERE promo_number IN
                    (SELECT DISTINCT promo_number
                           FROM OFFERS NATURAL JOIN INCLUDES
                           WHERE store_number = inp_store_number AND coffee_id = inp_coffee_id
                    );
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 7
CREATE OR REPLACE FUNCTION euclidean_distance(inp_lat_1 float, inp_lon_1 float, inp_lat_2 float, inp_lon_2 float)
RETURNS float
AS $$
DECLARE
    euclidean_distance float;
BEGIN
    RETURN SQRT(POWER(inp_lat_2 - inp_lat_1, 2) + POWER(inp_lon_2 - inp_lon_1, 2));
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_closest_stores(inp_latitude float, inp_longitude float)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
                FROM STORE WHERE (latitude, longitude) IN
                    (SELECT latitude, longitude
                    FROM STORE
                    ORDER BY euclidean_distance(10, 10, latitude, longitude) ASC
                    LIMIT 1
                    );
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_closest_stores_by_promo_number(inp_latitude float, inp_longitude float, inp_promo_number int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
                 FROM
                    (SELECT *
                        FROM STORE NATURAL JOIN OFFERS
                        WHERE promo_number = inp_promo_number
                     ) AS STORE_OFFERS_PROMO
                 WHERE (STORE_OFFERS_PROMO.latitude, STORE_OFFERS_PROMO.longitude) IN
                    (SELECT latitude, longitude
                        FROM STORE
                        ORDER BY euclidean_distance(10, 10, latitude, longitude) ASC
                        LIMIT 1
                    );
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 8
CREATE OR REPLACE FUNCTION check_if_loyalty_program_exists(inp_loyalty_level varchar(10))
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM LOYALTY_PROGRAM
                    WHERE loyalty_level = inp_loyalty_level );
$$
LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE add_or_update_loyalty_program(inp_loyalty_level varchar(10), inp_total_points_value_unlocked_at float, inp_booster_value float)
LANGUAGE plpgsql
AS $$
BEGIN
    IF check_if_loyalty_program_exists(inp_loyalty_level) THEN
        UPDATE LOYALTY_PROGRAM SET (loyalty_level, total_points_value_unlocked_at, booster_value) = (inp_loyalty_level, inp_total_points_value_unlocked_at, inp_booster_value)
        WHERE loyalty_level = inp_loyalty_level;
    ELSE
        INSERT INTO LOYALTY_PROGRAM (loyalty_level, total_points_value_unlocked_at, booster_value)
        VALUES (inp_loyalty_level, inp_total_points_value_unlocked_at, inp_booster_value);
    END IF;
END;
$$;

-- Task 9
CREATE OR REPLACE PROCEDURE add_customer(inp_first_name varchar(50), inp_last_name varchar(50), inp_mid_initial char(1), inp_birth_day char(2), inp_birth_month char(3), inp_phone_number varchar(16), inp_phone_type varchar(6))
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_day, birth_month, phone_number, phone_type)
    VALUES (inp_first_name, inp_last_name, inp_mid_initial, inp_birth_day, inp_birth_month, inp_phone_number, inp_phone_type);
END;
$$;

CREATE OR REPLACE FUNCTION get_customer_id(inp_phone_number varchar(16))
RETURNS int
AS $$
DECLARE
    customer_id int;
BEGIN
    SELECT * INTO customer_id
    FROM CUSTOMER
    WHERE phone_number = inp_phone_number;

    RETURN customer_id;
END;
$$ LANGUAGE plpgsql;

-- Task 10
CREATE OR REPLACE FUNCTION get_customer_current_points(inp_customer_id int)
RETURNS float
AS $$
DECLARE
    ret_current_points float;
BEGIN
    SELECT current_points INTO ret_current_points
    FROM CUSTOMER
    WHERE customer_id = inp_customer_id;

    RETURN ret_current_points;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_customer_total_points(inp_customer_id int)
RETURNS float
AS $$
DECLARE
    ret_total_points float;
BEGIN
    SELECT total_points INTO ret_total_points
    FROM CUSTOMER
    WHERE customer_id = inp_customer_id;

    RETURN ret_total_points;
END;
$$ LANGUAGE plpgsql;

-- Task 11
CREATE OR REPLACE FUNCTION get_customers_ranked_by_total_points()
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
        FROM CUSTOMER
        ORDER BY total_points DESC;
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_customers_ranked_by_current_points()
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
        FROM CUSTOMER
        ORDER BY current_points DESC;
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_customers_ranked_by_current_points_grouped_by_loyalty_level()
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *, RANK() OVER(
        PARTITION BY loyalty_level ORDER BY current_points DESC) AS rank
    FROM CUSTOMER;

    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 12
-- Assumptions:
---- A Customer must exist in the database that corresponds to the inp_customer_id used in SALE relation entity
---- A Store must exist in the database that corresponds to the inp_store_number used in Records relation entity
---- A Coffee must exist in the database that corresponds to each of the inp_coffee_id used in Records relation entity
---- A Coffee must be featured at the specified Store (inp_store_id) for each inp_coffee_id used in Records relation entity
---- A new tuple must be inserted into Records relation table after a new tuple is inserted into the Sale table
---- If a tuple already exists in Records relation table for a Sale's coffees, and the same coffee is inserted,
------- update instead on increment tuple's quantity value by 1
---- A new Sale's inp_redeemed_portion value must be <= the specified Customer's (inp_customer_id) current_points value
CREATE OR REPLACE FUNCTION check_if_customer_exists(inp_customer_id int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM CUSTOMER
                    WHERE customer_id = inp_customer_id );
$$
LANGUAGE SQL;

ALTER TABLE SALE
   ADD CONSTRAINT customer_exists CHECK (check_if_customer_exists(customer_id));

ALTER TABLE RECORDS
    ADD CONSTRAINT store_exists CHECK (check_if_store_exists(store_number));

ALTER TABLE RECORDS
    ADD CONSTRAINT coffee_exists CHECK (check_if_coffee_exists(coffee_id));

CREATE OR REPLACE FUNCTION check_if_store_features_coffee(inp_store_number int, inp_coffee_id int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM FEATURES
                    WHERE store_number = inp_store_number
                    AND coffee_id = inp_coffee_id
               );
$$
LANGUAGE SQL;

ALTER TABLE RECORDS
    ADD CONSTRAINT store_features_coffee CHECK (check_if_store_features_coffee(store_number, coffee_id));

CREATE OR REPLACE FUNCTION check_if_records_exists(inp_purchase_id int, inp_store_number int, inp_coffee_id int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM RECORDS
                    WHERE (purchase_id, store_number, coffee_id) = (inp_purchase_id, inp_store_number, inp_coffee_id));
$$
LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE add_records_or_increment_records_quantity(inp_purchase_id int, inp_store_number int, inp_coffee_id int, inp_purchased_portion float, inp_redeemed_portion float)
LANGUAGE plpgsql
AS $$
BEGIN
    IF check_if_records_exists(inp_purchase_id, inp_store_number, inp_coffee_id) THEN
        UPDATE RECORDS SET (quantity, purchased_portion, redeemed_portion) = (quantity + 1, purchased_portion + inp_purchased_portion, redeemed_portion + inp_redeemed_portion)
        WHERE (purchase_id, store_number, coffee_id) = (inp_purchase_id, inp_store_number, inp_coffee_id);
    ELSE
        INSERT INTO RECORDS (purchase_id, store_number, coffee_id, purchased_portion, redeemed_portion)
        VALUES (inp_purchase_id, inp_store_number, inp_coffee_id, inp_purchased_portion, inp_redeemed_portion);
    END IF;
END;
$$;

CREATE OR REPLACE FUNCTION add_new_sale(inp_customer_id int, inp_purchased_time timestamp)
RETURNS INT
AS $$
DECLARE
    new_purchase_id int;
BEGIN
    INSERT INTO SALE (customer_id, purchased_time)
    VALUES (inp_customer_id, inp_purchased_time)
    RETURNING SALE.purchase_id INTO new_purchase_id;

    RETURN new_purchase_id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_coffee_redeem_points(inp_coffee_id int)
RETURNS float
AS $$
DECLARE
    ret_redeem_points float;
BEGIN
    SELECT redeem_points INTO ret_redeem_points
    FROM COFFEE
    WHERE coffee_id = inp_coffee_id;

    RETURN ret_redeem_points;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_coffee_price(inp_coffee_id int)
RETURNS float
AS $$
DECLARE
    ret_price float;
BEGIN
    SELECT price INTO ret_price
    FROM COFFEE
    WHERE coffee_id = inp_coffee_id;

    RETURN ret_price;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_sale_balance(inp_purchase_id int, inp_balance float)
RETURNS int
AS $$
BEGIN
    UPDATE SALE SET balance = inp_balance
    WHERE purchase_id = inp_purchase_id;

    RETURN inp_purchase_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_customer_current_points(inp_customer_id int, inp_current_points float)
RETURNS int
AS $$
BEGIN
    UPDATE CUSTOMER SET current_points = inp_current_points
    WHERE customer_id = inp_customer_id;

    RETURN inp_customer_id;
END;
$$ LANGUAGE plpgsql;

-- Task 13
CREATE OR REPLACE FUNCTION get_coffees()
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
    FROM COFFEE;

    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 14
CREATE OR REPLACE FUNCTION get_coffees_by_intensity_and_two_keywords(inp_intensity int, inp_kw1 varchar(50), inp_kw2 varchar(50))
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT *
    FROM COFFEE
    WHERE intensity = inp_intensity
    AND POSITION(inp_kw1 IN coffee_name) > 0
    AND POSITION(inp_kw2 IN coffee_name) > 0;

    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Trigger Assumptions:
---- Trigger 1:
----- When a customer makes a Sale, if the Birth_Month and Birth_Day of the specified Customer_Id
----- matches the Sale's date, the final reward points earned for purchasing a coffee will be multiplied by 1.10 (10%).
---- Trigger 3:
----- After a customer makes a sale, the final reward points value calculated for a sale will be
----- added to the Customer's Current_Points and Total_Points.
CREATE OR REPLACE FUNCTION get_coffee_reward_points(inp_coffee_id int)
RETURNS float
AS $$
DECLARE
    ret_redeem_points float;
BEGIN
    SELECT redeem_points INTO ret_redeem_points
    FROM COFFEE
    WHERE coffee_id = inp_coffee_id;

    RETURN ret_redeem_points;
END;
$$ LANGUAGE plpgsql;

-- CREATE OR REPLACE FUNCTION is_customer_birthday(inp_customer_id int)
-- RETURNS BOOLEAN
-- AS $$
-- DECLARE
--     clock_date date;
--     clock_day char(2);
--     clock_numeric_month char(2);
--     customer_birth_day char(2);
--     customer_birth_month char(3);
--     ret bool;
-- BEGIN
--     SELECT p_date INTO clock_date FROM CLOCK;
--
--     SELECT birth_day, birth_month INTO customer_birth_day, customer_birth_month
--     FROM CUSTOMER
--     WHERE customer_id = inp_customer_id;
--
--     SELECT extract(DAY FROM clock_date) INTO clock_day;
--     SELECT extract(MONTH FROM clock_date) INTO clock_numeric_month;
--
--     IF FOUND THEN
--        CASE clock_numeric_month
--            WHEN '01' AND customer_birth_month = 'jan' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '02' AND customer_birth_month = 'feb' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '03' AND customer_birth_month = 'mar' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '04' AND customer_birth_month = 'apr' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '05' AND customer_birth_month = 'may' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '06' AND customer_birth_month = 'jun' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '07' AND customer_birth_month = 'jul' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '08' AND customer_birth_month = 'aug' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '09' AND customer_birth_month = 'sep' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '10' AND customer_birth_month = 'oct' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '11' AND customer_birth_month = 'nov' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--            WHEN '12' AND customer_birth_month = 'dec' AND clock_day = customer_birth_day THEN
--             ret := TRUE;
--       END CASE;
--     END IF;
--
--     IF ret = TRUE THEN
--         RETURN TRUE;
--     ELSE
--         RETURN FALSE;
--     END IF;
--
-- END;
-- $$
-- LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_customer_current_points_and_total_points(inp_customer_id int, inp_current_points float, inp_total_points float)
RETURNS int
AS $$
BEGIN
    UPDATE CUSTOMER SET (current_points, total_points) = (inp_current_points, inp_total_points)
    WHERE customer_id = inp_customer_id;

    RETURN inp_customer_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION after_update_on_sale()
RETURNS trigger AS
$$
DECLARE
    total_earned_points float;
    current_reward_points float;
    current_total_points float;
BEGIN
    SELECT SUM(reward_points) INTO total_earned_points
    FROM COFFEE
    WHERE coffee_id IN (
        SELECT coffee_id
        FROM SALE NATURAL JOIN RECORDS
        WHERE purchase_id = old.purchase_id
    );

    current_reward_points := get_customer_current_points(old.customer_id);
    current_total_points := get_customer_total_points(old.customer_id);

--     IF (is_customer_birthday(old.customer_id)) THEN
--         total_earned_points := total_earned_points * 1.10;
--     END IF;

    current_reward_points := current_reward_points + total_earned_points;
    current_total_points := current_total_points + total_earned_points;

    PERFORM update_customer_current_points_and_total_points(old.customer_id, current_reward_points, current_total_points);
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS after_update_on_sale ON SALE;
CREATE TRIGGER after_update_on_sale
AFTER UPDATE ON SALE FOR EACH ROW EXECUTE PROCEDURE after_update_on_sale();

---- Trigger 2:
----- For different Loyalty_Level of Loyalty_Program, there’s a
----- different Booster_value that is multiplied by the Reward_Points
----- for the final reward points earned for purchasing a Coffee.

---- Trigger 4:
----- Customer’s Loyalty_Level will get updated if their Total_Points
----- increases to a certain level (Total_Points_Value_Unlocked_At).
---- Trigger 5:
----- A Promotion (by Promotion_Id) will be removed whenever it's end_date
----- is equal to the current date.

