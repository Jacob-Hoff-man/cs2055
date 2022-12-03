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
$$

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

----------------------------------------------------------------------
-- PROCEDURES AND FUNCTIONS
----------------------------------------------------------------------
-- CREATE OR REPLACE PROCEDURE transfer_funds(from_account int, to_account int,  amount decimal(20, 2))
-- LANGUAGE plpgsql
-- AS $$
-- DECLARE
--    from_account_balance numeric(15, 3);
-- BEGIN
--     SELECT balance INTO from_account_balance
--     FROM ACCOUNT
--     WHERE acc_no = from_account;
--
--     IF from_account_balance > amount THEN
--         UPDATE ACCOUNT SET balance = balance - amount
--         WHERE acc_no = from_account;
--         UPDATE ACCOUNT SET balance = balance + amount
--         WHERE acc_no = to_account;
--     ELSE
--         RAISE NOTICE 'ERROR: balance is too low';
--     end if;
-- end;
-- $$;
--
--
-- CREATE OR REPLACE FUNCTION can_pay_loan (customer_ssn char(9))
-- RETURNS BOOLEAN
-- AS $$
-- DECLARE
--     can_pay BOOLEAN := false;
--     total_loan numeric(15, 2);
--     total_balance numeric(15, 2);
-- BEGIN
--     SELECT SUM(amount) INTO total_loan
--     FROM LOAN
--     WHERE ssn = $1;
--
--     SELECT SUM(balance) INTO total_balance
--     FROM ACCOUNT
--     WHERE ssn = $1;
--
--     IF (total_balance >= total_loan) OR total_loan is NULL THEN
--         can_pay := true;
--     end if;
--
--     RETURN can_pay;
-- end;
-- $$ LANGUAGE plpgsql;

-- CREATE OR REPLACE FUNCTION fn1()
-- RETURNS trigger AS
-- $$
-- BEGIN
--     UPDATE CUSTOMER SET num_accounts = num_accounts + 1
--     WHERE ssn = new.ssn;
-- END;
-- $$
-- LANGUAGE plpgsql;
--
-- DROP TRIGGER IF EXISTS trig_1 ON account;
-- CREATE TRIGGER trig_1
--     AFTER INSERT
--     ON account
--     for each row EXECUTE PROCEDURE fn1();


-- CREATE OR REPLACE PROCEDURE loan_payment(from_account varchar(15), customer_ssn char(9), bank_code char(4), loan_open date, payment decimal(20, 2))
-- LANGUAGE plpgsql
-- AS $$
-- DECLARE
--    account_balance numeric(15, 3);
--    loan_amount  numeric(15, 3);
-- BEGIN
--     SELECT A.balance INTO account_balance
--     FROM ACCOUNT A
--     WHERE A.acc_no = from_account
--         AND A.ssn = customer_ssn;
--
--     SELECT LOAN.amount INTO loan_amount
--     FROM LOAN
--     WHERE ssn = customer_ssn
--         AND code = bank_code
--         AND open_date = loan_open;
--
--     IF account_balance > payment THEN
--         UPDATE ACCOUNT
--         SET balance = balance - payment
--         WHERE acc_no = from_account;
--
--         IF account_balance >= loan_amount THEN
--             UPDATE LOAN
--             SET amount = 0,
--                 close_date = current_date
--             WHERE ssn = customer_ssn
--                 AND code = bank_code
--                 AND open_date = loan_open;
--         ELSE
--             UPDATE LOAN
--             SET amount = amount - payment
--             WHERE ssn = customer_ssn
--                 AND code = bank_code
--                 AND open_date = loan_open;
--         end if;
--     ELSE
--         RAISE NOTICE 'ERROR: balance is too low';
--     end if;
-- end;
-- $$;

-- CREATE OR REPLACE FUNCTION before_insert_on_customer()
-- RETURNS trigger AS
-- $$
-- BEGIN
--     new.name := upper(new.name);
--     return new;
-- END;
-- $$
-- LANGUAGE plpgsql;
--
-- drop trigger if exists before_insert_on_customer on customer;
-- create trigger before_insert_on_customer
--     before insert on customer for each row execute procedure before_insert_on_customer();
