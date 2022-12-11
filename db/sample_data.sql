---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
-------------------------------------------
-- Stores
INSERT INTO STORE (store_name, longitude, latitude, store_type) VALUES ('CB1', 20.1, 50.2, 'kiosk');
INSERT INTO STORE (store_name, longitude, latitude, store_type) VALUES ('CB2', 50.1, 100.2, 'kiosk');
INSERT INTO STORE (store_name, longitude, latitude, store_type) VALUES ('CB3', 10.1, 25.2, 'sitting');

-- Loyalty_Programs
INSERT INTO LOYALTY_PROGRAM VALUES ('basic', 0, 1.0);
INSERT INTO LOYALTY_PROGRAM VALUES ('bronze', 500, 1.25);
INSERT INTO LOYALTY_PROGRAM VALUES ('silver', 2500, 1.5);
INSERT INTO LOYALTY_PROGRAM VALUES ('gold', 5000, 1.75);
INSERT INTO LOYALTY_PROGRAM VALUES ('platinum', 50000, 2.0);
INSERT INTO LOYALTY_PROGRAM VALUES ('diamond', 500000, 4.0);

-- Customers
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Jacob', 'Hof', 'A', 'jul', '08', '111-222-3333', 'mobile', 'diamond', 10000, 500000);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Kairuo', 'Yan', 'K', 'jan', '01', '222-222-3333', 'home', 'basic', 0, 0);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Bob', 'Ross', 'K', 'feb', '02', '333-222-3333', 'work', 'silver', 2500, 2500);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Luke', 'Skywalker', 'A', 'mar', '03', '444-222-3333', 'other', 'gold', 5000, 5000);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Mike', 'Trout', 'B', 'apr', '04', '555-222-3333', 'mobile', 'platinum', 50000, 50000);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Rock', 'Paper', 'S', 'may', '05', '666-222-3333', 'home', 'bronze', 500, 500);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Jason', 'Freddy', 'M', 'jun', '06', '777-222-3333', 'work', 'basic', 250, 250);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Count', 'Drake', 'V', 'jul', '07', '888-222-3333', 'other', 'bronze', 1000, 1000);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type, loyalty_level, current_points, total_points) VALUES ('Bruce', 'Wayne', 'B', 'aug', '08', '999-222-3333', 'mobile', 'silver', 0, 2500);
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Alex', 'Hero', 'E', 'sep', '09', '123-222-3333', 'home');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Ziggy', 'Swifty', 'T', 'oct', '10', '456-222-3333', 'work');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('CJ', 'Smith', 'A', 'nov', '11', '789-222-3333', 'other');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Jake', 'Hof', 'A', 'dec', '12', '111-333-4444', 'mobile');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Zach', 'Blaze', 'J', 'jan', '13', '222-333-4444', 'home');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Devin', 'Eleven', 'M', 'feb', '14', '333-333-4444', 'work');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Rick', 'Morty', 'S', 'mar', '15', '444-333-4444', 'other');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Joe', 'Average', 'A', 'apr', '16', '555-333-4444', 'mobile');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Panos', 'Crythanthis', 'A', 'may', '17', '666-333-4444', 'home');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Brian', 'Nixon', 'J', 'jun', '18', '777-333-4444', 'work');
INSERT INTO CUSTOMER (first_name, last_name, mid_initial, birth_month, birth_day, phone_number, phone_type) VALUES ('Ralph', 'Lauren', 'P', 'jul', '19', '888-333-4444', 'other');

-- Coffees
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Espresso', 'The espresso, also known as a short black, is approximately 1 oz. of highly concentrated coffee. Although simple in appearance, it can be difficult to master.', 'Canada', 6, 3.0, 90.0, 30.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Double Espresso', 'A double espresso may also be listed as doppio, which is the Italian word for double. This drink is highly concentrated and strong.', 'Canada', 12, 5.0, 180.0, 60.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Red Eye', 'The red eyes purpose is to add a boost of caffeine to your standard cup of coffee.', 'Canada', 8, 4.50, 135.00, 45.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Black Eye', 'The black eye is just the doubled version of the red eye and is very high in caffeine.', 'Canada', 12, 5.50, 165.0, 55.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Americano', 'Americanos are popular breakfast drinks and thought to have originated during World War II. Soldiers would add water to their coffee to extend their rations farther. The water dilutes the espresso while still maintaining a high level of caffeine.', 'Canada', 12, 3.0, 90.0, 30.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Long Black', 'The long black is a similar coffee drink to the americano, but it originated in New Zealand and Australia. It generally has more crema than an americano.', 'Canada', 5, 3.50, 105.0, 35.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Macchiato', 'The word macchiato means mark or stain. This is in reference to the mark that steamed milk leaves on the surface of the espresso as it is dashed into the drink. Flavoring syrups are often added to the drink according to customer preference.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Breve', 'The breve provides a decadent twist on the average espresso, adding steamed half-and-half to create a rich and creamy texture.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Cappuccino', 'This creamy coffee drink is usually consumed at breakfast time in Italy and is loved in the United States as well. It is usually associated with indulgence and comfort because of its thick foam layer and additional flavorings that can be added to it.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Cafe Latte', 'Cafe lattes are considered an introductory coffee drink since the acidity and bitterness of coffee are cut by the amount of milk in the beverage. Flavoring syrups are often added to the latte for those who enjoy sweeter drinks.', 'Canada', 2, 5.50, 165.0, 55.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Mocha', 'The mocha is considered a coffee and hot chocolate hybrid. The chocolate powder or syrup gives it a rich and creamy flavor and cuts the acidity of the espresso.', 'Canada', 3, 5.50, 165.0, 55.0);
INSERT INTO COFFEE (coffee_name, description, country, intensity, price, redeem_points, reward_points) VALUES ('Iced Coffee', 'Iced coffees become very popular in the summertime in the United States. The recipes do have some variance, with some locations choosing to interchange milk with water in the recipe. Different flavoring syrups will be added per customer preference.', 'Canada', 3, 4.75, 142.5, 47.50);

-- Promos
INSERT INTO PROMOTION (promo_name, start_date, end_date) VALUES ('Promo 1', '2022-10-01', '2022-11-01');
INSERT INTO PROMOTION (promo_name, start_date, end_date) VALUES ('Promo 2', '2021-10-01', '2023-12-30');
INSERT INTO PROMOTION (promo_name, start_date, end_date) VALUES ('Promo 3', '2023-01-01', '2023-07-01');

-- Purchase (Sale)
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(20);
INSERT INTO SALE (customer_id) VALUES(5);
INSERT INTO SALE (customer_id) VALUES(3);
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(8);
INSERT INTO SALE (customer_id) VALUES(14);
INSERT INTO SALE (customer_id) VALUES(15);
INSERT INTO SALE (customer_id) VALUES(12);
INSERT INTO SALE (customer_id) VALUES(11);
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(3);
INSERT INTO SALE (customer_id) VALUES(9);
INSERT INTO SALE (customer_id) VALUES(4);
INSERT INTO SALE (customer_id) VALUES(2);
INSERT INTO SALE (customer_id) VALUES(11);
INSERT INTO SALE (customer_id) VALUES(13);
INSERT INTO SALE (customer_id) VALUES(10);
INSERT INTO SALE (customer_id) VALUES(16);
INSERT INTO SALE (customer_id) VALUES(17);
INSERT INTO SALE (customer_id) VALUES(18);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(20);
INSERT INTO SALE (customer_id) VALUES(5);
INSERT INTO SALE (customer_id) VALUES(3);
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(8);
INSERT INTO SALE (customer_id) VALUES(14);
INSERT INTO SALE (customer_id) VALUES(15);
INSERT INTO SALE (customer_id) VALUES(12);
INSERT INTO SALE (customer_id) VALUES(11);
INSERT INTO SALE (customer_id) VALUES(1);
INSERT INTO SALE (customer_id) VALUES(3);
INSERT INTO SALE (customer_id) VALUES(9);
INSERT INTO SALE (customer_id) VALUES(4);
INSERT INTO SALE (customer_id) VALUES(2);
INSERT INTO SALE (customer_id) VALUES(11);
INSERT INTO SALE (customer_id) VALUES(13);
INSERT INTO SALE (customer_id) VALUES(10);
INSERT INTO SALE (customer_id) VALUES(16);
INSERT INTO SALE (customer_id) VALUES(17);
INSERT INTO SALE (customer_id) VALUES(18);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);
INSERT INTO SALE (customer_id) VALUES(19);

-- Includes
INSERT INTO INCLUDES VALUES (1,3);
INSERT INTO INCLUDES VALUES (1,8);
INSERT INTO INCLUDES VALUES (1,5);
INSERT INTO INCLUDES VALUES (2,11);
INSERT INTO INCLUDES VALUES (3,1);
INSERT INTO INCLUDES VALUES (2,7);
INSERT INTO INCLUDES VALUES (2,6);
INSERT INTO INCLUDES VALUES (3,6);
INSERT INTO INCLUDES VALUES (3,4);

-- Offers
INSERT INTO OFFERS VALUES(1,1);
INSERT INTO OFFERS VALUES(1,3);
INSERT INTO OFFERS VALUES(1,2);
INSERT INTO OFFERS VALUES(2,3);
INSERT INTO OFFERS VALUES(3,3);
INSERT INTO OFFERS VALUES(2,1);
INSERT INTO OFFERS VALUES(2,2);
INSERT INTO OFFERS VALUES(3,2);
INSERT INTO OFFERS VALUES(3,1);

-- Features
INSERT INTO FEATURES VALUES(1,1);
INSERT INTO FEATURES VALUES(2,1);
INSERT INTO FEATURES VALUES(3,1);
INSERT INTO FEATURES VALUES(4,1);
INSERT INTO FEATURES VALUES(5,1);
INSERT INTO FEATURES VALUES(6,1);
INSERT INTO FEATURES VALUES(7,1);
INSERT INTO FEATURES VALUES(8,1);
INSERT INTO FEATURES VALUES(9,1);
INSERT INTO FEATURES VALUES(10,1);
INSERT INTO FEATURES VALUES(11,1);
INSERT INTO FEATURES VALUES(12,1);
INSERT INTO FEATURES VALUES(1,2);
INSERT INTO FEATURES VALUES(2,2);
INSERT INTO FEATURES VALUES(3,2);
INSERT INTO FEATURES VALUES(4,2);
INSERT INTO FEATURES VALUES(5,2);
INSERT INTO FEATURES VALUES(6,2);
INSERT INTO FEATURES VALUES(7,2);
INSERT INTO FEATURES VALUES(8,2);
INSERT INTO FEATURES VALUES(9,2);
INSERT INTO FEATURES VALUES(10,2);
INSERT INTO FEATURES VALUES(11,2);
INSERT INTO FEATURES VALUES(12,2);
INSERT INTO FEATURES VALUES(1,3);
INSERT INTO FEATURES VALUES(2,3);
INSERT INTO FEATURES VALUES(3,3);
INSERT INTO FEATURES VALUES(4,3);
INSERT INTO FEATURES VALUES(5,3);
INSERT INTO FEATURES VALUES(6,3);
INSERT INTO FEATURES VALUES(7,3);
INSERT INTO FEATURES VALUES(8,3);
INSERT INTO FEATURES VALUES(9,3);
INSERT INTO FEATURES VALUES(10,3);
INSERT INTO FEATURES VALUES(11,3);
INSERT INTO FEATURES VALUES(12,3);

-- Records
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(1,1,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(2,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(3,3,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(3,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(4,1,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(5,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(6,3,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(7,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(8,1,5);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(9,2,5);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(10,3,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(11,2,9);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(12,1,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(13,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(14,3,12);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(15,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(16,1,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(17,2,8);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(18,3,7);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(19,2,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(20,1,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(21,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(22,3,12);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(23,1,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(24,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(25,3,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(26,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(27,1,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(28,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(29,3,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(30,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(31,1,5);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(32,2,5);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(33,3,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(34,2,9);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(35,1,1);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(36,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(37,3,12);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(38,2,3);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(39,1,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(40,2,8);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(41,3,7);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(42,2,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(43,1,10);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(44,2,2);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(45,3,12);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(46,2,9);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(47,1,11);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(48,2,6);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(49,3,12);
INSERT INTO RECORDS (purchase_id, store_number, coffee_id) VALUES(50,2,3);

INSERT INTO CLOCK VALUES('2022-07-09');