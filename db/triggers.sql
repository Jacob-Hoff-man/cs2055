---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
-------------------------------------------

-- TASKS --
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
   ADD CONSTRAINT coffee_exists CHECK (check_if_coffee_exists(coffee_id)) DEFERRABLE;

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
   ADD CONSTRAINT promotion_exists CHECK (check_if_promotion_exists(promo_number)) DEFERRABLE;

CREATE OR REPLACE FUNCTION check_if_store_exists(inp_store_number int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM STORE
                    WHERE store_number = inp_store_number );
$$
LANGUAGE SQL;

ALTER TABLE OFFERS
   ADD CONSTRAINT store_exists CHECK (check_if_store_exists(store_number)) DEFERRABLE;

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

CREATE OR REPLACE FUNCTION get_stores_with_promotions_by_coffee_id(inp_coffee_id int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
BEGIN
    OPEN ref FOR SELECT * FROM STORE WHERE store_number IN (
        SELECT DISTINCT store_number
        FROM OFFERS NATURAL JOIN INCLUDES
        WHERE coffee_id = inp_coffee_id
    );
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
                    ORDER BY euclidean_distance(inp_latitude, inp_longitude, latitude, longitude) ASC
                    FETCH FIRST 1 ROWS WITH TIES
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
   ADD CONSTRAINT customer_exists CHECK (check_if_customer_exists(customer_id)) DEFERRABLE;

ALTER TABLE RECORDS
    ADD CONSTRAINT store_exists CHECK (check_if_store_exists(store_number)) DEFERRABLE;

ALTER TABLE RECORDS
    ADD CONSTRAINT coffee_exists CHECK (check_if_coffee_exists(coffee_id)) DEFERRABLE;

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
    ADD CONSTRAINT store_features_coffee CHECK (check_if_store_features_coffee(store_number, coffee_id)) DEFERRABLE;

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

-- Task 15
CREATE OR REPLACE FUNCTION get_top_k_stores_by_highest_revenue_in_x_months(inp_k int, inp_months int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
    num_days int := 30 * inp_months;
    end_date date;
    start_date date;
BEGIN
    end_date := get_p_clock_date();
    start_date := end_date - num_days;

    OPEN ref FOR SELECT store_number
                 FROM STORE NATURAL JOIN (
                     SELECT *
                     FROM RECORDS NATURAL JOIN SALE
                     WHERE DATE(purchased_time) >= start_date
                     AND DATE(purchased_time) <= end_date
                 ) AS X_MONTHS_RECORDS
                 GROUP BY store_number
                 ORDER BY SUM(purchased_portion) DESC
                 FETCH FIRST inp_k ROWS WITH TIES;
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- Task 16
CREATE OR REPLACE FUNCTION get_top_k_customers_by_highest_purchased_sum_in_x_months(inp_k int, inp_months int)
RETURNS refcursor
AS $$
DECLARE
    ref refcursor;
    num_days int := 30 * inp_months;
    end_date date;
    start_date date;
BEGIN
    end_date := get_p_clock_date();
    start_date := end_date - num_days;

    OPEN ref FOR SELECT customer_id
                 FROM SALE NATURAL JOIN RECORDS
                 WHERE DATE(purchased_time) >= start_date
                 AND DATE(purchased_time) <= end_date
                 GROUP BY customer_id
                 ORDER BY SUM(purchased_portion) DESC
                 FETCH FIRST inp_k ROWS WITH TIES;
    RETURN ref;
END;
$$ LANGUAGE plpgsql;

-- TRIGGERS --
-- Trigger Assumptions:
---- Trigger 1:
----- When a customer makes a Sale, if the Birth_Month and Birth_Day of the specified Customer_Id
----- matches the Sale's date, the final reward points earned for purchasing a coffee will be multiplied by 1.10 (10%).
----- For different Loyalty_Level of Loyalty_Program, there’s a
----- different Booster_value that is multiplied by the Reward_Points
----- for the final reward points earned for purchasing a Coffee.
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

CREATE OR REPLACE FUNCTION get_customer_birth_day(inp_customer_id int)
RETURNS char(2)
AS $$
DECLARE
    ret_birth_day float;
BEGIN
    SELECT birth_day INTO ret_birth_day
    FROM CUSTOMER
    WHERE customer_id = inp_customer_id;

    RETURN ret_birth_day;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_customer_birth_month(inp_customer_id int)
RETURNS char(3)
AS $$
DECLARE
    ret_birth_month char(3);
BEGIN
    SELECT birth_month INTO ret_birth_month
    FROM CUSTOMER
    WHERE customer_id = inp_customer_id;

    RETURN ret_birth_month;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_p_clock_day()
RETURNS char(2)
AS $$
DECLARE
    ret_day_string char(2);
BEGIN
    SELECT cast(date_part('day', p_date) as char(2)) INTO ret_day_string
    FROM CLOCK;

    RETURN ret_day_string;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_p_clock_month()
RETURNS char(3)
AS $$
DECLARE
    ret_month float;
    ret_month_string char(3);
BEGIN
    SELECT date_part('month', p_date) INTO ret_month
    FROM CLOCK;

    CASE ret_month
        WHEN '1' THEN ret_month_string := 'jan';
        WHEN '2' THEN ret_month_string := 'feb';
        WHEN '3' THEN ret_month_string := 'mar';
        WHEN '4' THEN ret_month_string := 'apr';
        WHEN '5' THEN ret_month_string := 'may';
        WHEN '6' THEN ret_month_string := 'jun';
        WHEN '7' THEN ret_month_string := 'jul';
        WHEN '8' THEN ret_month_string := 'aug';
        WHEN '9' THEN ret_month_string := 'sep';
        WHEN '10' THEN ret_month_string := 'oct';
        WHEN '11' THEN ret_month_string := 'nov';
        WHEN '12' THEN ret_month_string := 'dec';
    END CASE;

    RETURN ret_month_string;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_timestamp_day(inp_timestamp timestamp)
RETURNS char(2)
AS $$
BEGIN
    return cast(date_part('day', inp_timestamp) as char(2));
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_timestamp_month(inp_timestamp timestamp)
RETURNS char(3)
AS $$
DECLARE
    ret_month_string char(3);
BEGIN
    CASE date_part('month', inp_timestamp)
        WHEN '1' THEN ret_month_string := 'jan';
        WHEN '2' THEN ret_month_string := 'feb';
        WHEN '3' THEN ret_month_string := 'mar';
        WHEN '4' THEN ret_month_string := 'apr';
        WHEN '5' THEN ret_month_string := 'may';
        WHEN '6' THEN ret_month_string := 'jun';
        WHEN '7' THEN ret_month_string := 'jul';
        WHEN '8' THEN ret_month_string := 'aug';
        WHEN '9' THEN ret_month_string := 'sep';
        WHEN '10' THEN ret_month_string := 'oct';
        WHEN '11' THEN ret_month_string := 'nov';
        WHEN '12' THEN ret_month_string := 'dec';
    END CASE;

    RETURN ret_month_string;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION is_customer_birthday(inp_customer_id int)
RETURNS BOOLEAN
AS $$
DECLARE
    birth_month char(3);
    birth_day char(2);
    p_month char(3);
    p_day char(2);
BEGIN
    birth_day := get_customer_birth_day(inp_customer_id);
    birth_month := get_customer_birth_month(inp_customer_id);
    p_day := get_p_clock_day();
    p_month := get_p_clock_month();

    raise notice 'CUSTOMER BDAY Value: %', birth_day;
    raise notice 'CUSTOMER BMONTH Value: %', birth_month;
    raise notice 'CLOCK DAY Value: %', p_day;
    raise notice 'CLOCK MONTH Value: %', p_month;


    IF (birth_day = p_day AND birth_month = p_month) THEN
        return true;
    ELSE
        return false;
    END IF;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION is_sale_purchase_time_customer_birthday(inp_customer_id int, inp_timestamp timestamp)
RETURNS BOOLEAN
AS $$
DECLARE
    birth_month char(3);
    birth_day char(2);
    ts_month char(3);
    ts_day char(2);
BEGIN
    birth_day := get_customer_birth_day(inp_customer_id);
    birth_month := get_customer_birth_month(inp_customer_id);
    ts_day := get_timestamp_day(inp_timestamp);
    ts_month := get_timestamp_month(inp_timestamp);

    IF (birth_day = ts_day AND birth_month = ts_month) THEN
        return true;
    ELSE
        return false;
    END IF;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_customer_loyalty_level_booster_value(inp_customer_id int)
RETURNS float
AS $$
DECLARE
    ret_booster_value float;
BEGIN
    SELECT booster_value INTO ret_booster_value FROM LOYALTY_PROGRAM
    WHERE loyalty_level IN (
        SELECT loyalty_level FROM CUSTOMER
        WHERE customer_id = inp_customer_id
    );

    return ret_booster_value;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION customer_is_loyalty_member(inp_customer_id int)
RETURNS BOOLEAN
AS $$
SELECT EXISTS ( SELECT *
                    FROM CUSTOMER
                    WHERE customer_id = inp_customer_id
                    AND loyalty_level IS NOT NULL
);
$$
LANGUAGE SQL;

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
    SELECT SUM(reward_points * SALE_COFFEES.quantity) INTO total_earned_points
    FROM COFFEE NATURAL JOIN (
        SELECT coffee_id, quantity
        FROM SALE NATURAL JOIN RECORDS
        WHERE purchase_id = old.purchase_id
    ) AS SALE_COFFEES;

    current_reward_points := get_customer_current_points(old.customer_id);
    current_total_points := get_customer_total_points(old.customer_id);

--  give 10% additional points whenever !SALE PURCHASE TIME! is the customer's birthday
    IF (is_sale_purchase_time_customer_birthday(old.customer_id, old.purchased_time)) THEN
        total_earned_points := total_earned_points * 1.10;
    END IF;

--  give 10% additional points whenever p_clock is the customer's birthday
--     IF (is_customer_birthday(old.customer_id)) THEN
--         total_earned_points := total_earned_points * 1.10;
--     END IF;

--  give loyalty_level booster additional points
    total_earned_points := total_earned_points *
                           get_customer_loyalty_level_booster_value(old.customer_id);
-- loyalty_level for a customer has default values, so always != null... replaced for opitmization
--     IF (customer_is_loyalty_member(old.customer_id)) THEN
--         total_earned_points := total_earned_points *
--                                get_customer_loyalty_level_booster_value(old.customer_id);
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

----- Trigger 2:
----- Customer’s Loyalty_Level will get updated if their Total_Points
----- increases to a certain level (Total_Points_Value_Unlocked_At).
CREATE OR REPLACE FUNCTION get_highest_unlocked_loyalty_level(inp_total_points float)
RETURNS varchar(10) AS
$$
DECLARE
    highest_points_value_unlocked_at float;
    ret_loyalty_level varchar(10);
BEGIN
    SELECT MAX(total_points_value_unlocked_at) INTO highest_points_value_unlocked_at
    FROM (
        SELECT *
        FROM LOYALTY_PROGRAM
        WHERE inp_total_points >= total_points_value_unlocked_at
    )
    AS unlocked_loyalty_levels;

    SELECT loyalty_level INTO ret_loyalty_level
    FROM LOYALTY_PROGRAM
    WHERE total_points_value_unlocked_at = highest_points_value_unlocked_at;

    return ret_loyalty_level;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION before_update_on_customer()
RETURNS trigger AS
$$
DECLARE
    new_loyalty_level varchar(10);
BEGIN
    new_loyalty_level := get_highest_unlocked_loyalty_level(new.total_points);
    new.loyalty_level:= new_loyalty_level;
    return new;
END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS before_update_on_customer ON CUSTOMER;
CREATE TRIGGER before_update_on_customer
BEFORE UPDATE ON CUSTOMER FOR EACH ROW EXECUTE PROCEDURE before_update_on_customer();

---- Trigger 3:
----- A Promotion (by Promotion_Id) will be removed whenever it's end_date
----- is equal to the current date (p_date in CLOCK).
----- After update on CLOCK, remove any promos where p_date = end_date
CREATE OR REPLACE FUNCTION get_p_clock_date()
RETURNS date AS
$$
DECLARE
    ret_date date;
BEGIN
    SELECT p_date INTO ret_date
    FROM CLOCK;
    RETURN ret_date;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE update_clock_date(inp_date date)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE CLOCK SET p_date = inp_date
    WHERE p_date = get_p_clock_date();
END;
$$;

CREATE OR REPLACE FUNCTION after_update_on_clock()
RETURNS trigger
AS
$$
BEGIN
    DELETE FROM PROMOTION
    WHERE promo_number IN (
        SELECT promo_number
        FROM PROMOTION
        WHERE end_date <= new.p_date
    );
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS after_update_on_clock ON CLOCK;
CREATE TRIGGER after_update_on_clock
AFTER UPDATE ON CLOCK FOR EACH ROW EXECUTE PROCEDURE after_update_on_clock();

