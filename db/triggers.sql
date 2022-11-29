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