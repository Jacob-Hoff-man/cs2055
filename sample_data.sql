---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
-------------------------------------------
-- Stores
INSERT INTO STORE VALUES (1, 'CB1', 20.1, 50.2, 'kiosk');
INSERT INTO STORE VALUES (2, 'CB2', 50.1, 100.2, 'kiosk');
INSERT INTO STORE VALUES (3, 'CB3', 10.1, 25.2, 'sitting');

-- Loyalty_Programs
INSERT INTO LOYALTY_PROGRAM VALUES ('basic', 0, 1.0);
INSERT INTO LOYALTY_PROGRAM VALUES ('bronze', 500, 1.25);
INSERT INTO LOYALTY_PROGRAM VALUES ('silver', 2500, 1.5);
INSERT INTO LOYALTY_PROGRAM VALUES ('gold', 5000, 1.75);
INSERT INTO LOYALTY_PROGRAM VALUES ('platinum', 50000, 2.0);
INSERT INTO LOYALTY_PROGRAM VALUES ('diamond', 500000, 4.0);

-- Customers
INSERT INTO CUSTOMER VALUES (1, 'Jacob', 'Hof', 'A', 'jul', '08', '111-222-3333', 'mobile', 'diamond', 10000, 500000);
INSERT INTO CUSTOMER VALUES (2, 'Kairuo', 'Yan', 'K', 'jan', '01', '222-222-3333', 'home', 'basic', 0, 0);
INSERT INTO CUSTOMER VALUES (3, 'Bob', 'Ross', 'K', 'feb', '02', '333-222-3333', 'work', 'silver', 2500, 2500);
INSERT INTO CUSTOMER VALUES (4, 'Luke', 'Skywalker', 'A', 'mar', '03', '444-222-3333', 'other', 'gold', 5000, 5000);
INSERT INTO CUSTOMER VALUES (5, 'Mike', 'Trout', 'B', 'apr', '04', '555-222-3333', 'mobile', 'platinum', 50000, 50000);
INSERT INTO CUSTOMER VALUES (6, 'Rock', 'Paper', 'S', 'may', '05', '666-222-3333', 'home', 'bronze', 500, 500);
INSERT INTO CUSTOMER VALUES (7, 'Jason', 'Freddy', 'M', 'jun', '06', '777-222-3333', 'work', 'basic', 250, 250);
INSERT INTO CUSTOMER VALUES (8, 'Count', 'Drake', 'V', 'jul', '07', '888-222-3333', 'other', 'bronze', 1000, 1000);
INSERT INTO CUSTOMER VALUES (9, 'Bruce', 'Wayne', 'B', 'aug', '08', '999-222-3333', 'mobile', 'silver', 0, 2500);
INSERT INTO CUSTOMER VALUES (10, 'Alex', 'Hero', 'E', 'sep', '09', '123-222-3333', 'home');
INSERT INTO CUSTOMER VALUES (11, 'Ziggy', 'Swifty', 'T', 'oct', '10', '456-222-3333', 'work');
INSERT INTO CUSTOMER VALUES (12, 'CJ', 'Smith', 'A', 'nov', '11', '789-222-3333', 'other');
INSERT INTO CUSTOMER VALUES (13, 'Jake', 'Hof', 'A', 'dec', '12', '111-333-4444', 'mobile');
INSERT INTO CUSTOMER VALUES (14, 'Zach', 'Blaze', 'J', 'jan', '13', '222-333-4444', 'home');
INSERT INTO CUSTOMER VALUES (15, 'Devin', 'Eleven', 'M', 'feb', '14', '333-333-4444', 'work');
INSERT INTO CUSTOMER VALUES (16, 'Rick', 'Morty', 'S', 'mar', '15', '444-333-4444', 'other');
INSERT INTO CUSTOMER VALUES (17, 'Joe', 'Average', 'A', 'apr', '16', '555-333-4444', 'mobile');
INSERT INTO CUSTOMER VALUES (18, 'Panos', 'Crythanthis', 'A', 'may', '17', '666-333-4444', 'home');
INSERT INTO CUSTOMER VALUES (19, 'Brian', 'Nixon', 'J', 'jun', '18', '777-333-4444', 'work');
INSERT INTO CUSTOMER VALUES (20, 'Ralph', 'Lauren', 'P', 'jul', '19', '888-333-4444', 'other');

-- Coffees
INSERT INTO COFFEE VALUES (1, 'Espresso', 'The espresso, also known as a short black, is approximately 1 oz. of highly concentrated coffee. Although simple in appearance, it can be difficult to master.', 'Canada', 6, 3.0, 90.0, 30.0);
INSERT INTO COFFEE VALUES (2, 'Double Espresso', 'A double espresso may also be listed as doppio, which is the Italian word for double. This drink is highly concentrated and strong.', 'Canada', 12, 5.0, 180.0, 60.0);
INSERT INTO COFFEE VALUES (3, 'Red Eye', 'The red eyes purpose is to add a boost of caffeine to your standard cup of coffee.', 'Canada', 8, 4.50, 135.00, 45.0);
INSERT INTO COFFEE VALUES (4, 'Black Eye', 'The black eye is just the doubled version of the red eye and is very high in caffeine.', 'Canada', 12, 5.50, 165.0, 55.0);
INSERT INTO COFFEE VALUES (5, 'Americano', 'Americanos are popular breakfast drinks and thought to have originated during World War II. Soldiers would add water to their coffee to extend their rations farther. The water dilutes the espresso while still maintaining a high level of caffeine.', 'Canada', 12, 3.0, 90.0, 30.0);
INSERT INTO COFFEE VALUES (6, 'Long Black', 'The long black is a similar coffee drink to the americano, but it originated in New Zealand and Australia. It generally has more crema than an americano.', 'Canada', 5, 3.50, 105.0, 35.0);
INSERT INTO COFFEE VALUES (7, 'Macchiato', 'The word macchiato means mark or stain. This is in reference to the mark that steamed milk leaves on the surface of the espresso as it is dashed into the drink. Flavoring syrups are often added to the drink according to customer preference.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE VALUES (8, 'Breve', 'The breve provides a decadent twist on the average espresso, adding steamed half-and-half to create a rich and creamy texture.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE VALUES (9, 'Cappuccino', 'This creamy coffee drink is usually consumed at breakfast time in Italy and is loved in the United States as well. It is usually associated with indulgence and comfort because of its thick foam layer and additional flavorings that can be added to it.', 'Canada', 4, 5.00, 150.0, 50.0);
INSERT INTO COFFEE VALUES (10, 'Cafe Latte', 'Cafe lattes are considered an introductory coffee drink since the acidity and bitterness of coffee are cut by the amount of milk in the beverage. Flavoring syrups are often added to the latte for those who enjoy sweeter drinks.', 'Canada', 2, 5.50, 165.0, 55.0);
INSERT INTO COFFEE VALUES (11, 'Mocha', 'The mocha is considered a coffee and hot chocolate hybrid. The chocolate powder or syrup gives it a rich and creamy flavor and cuts the acidity of the espresso.', 'Canada', 3, 5.50, 165.0, 55.0);
INSERT INTO COFFEE VALUES (12, 'Iced Coffee', 'Iced coffees become very popular in the summertime in the United States. The recipes do have some variance, with some locations choosing to interchange milk with water in the recipe. Different flavoring syrups will be added per customer preference.', 'Canada', 3, 4.75, 142.5, 47.50);

-- Promos
INSERT INTO PROMOTION VALUES (1, 'Promo 1', '2022-10-01', '2022-11-01');
INSERT INTO PROMOTION VALUES (2, 'Promo 2', '2021-10-01', '2023-12-30');
INSERT INTO PROMOTION VALUES (3, 'Promo 3', '2023-01-01', '2023-07-01');

-- Purchase (Sale)
INSERT INTO SALE VALUES(1, 1, '13:20', 3.0, 3.0);
INSERT INTO SALE VALUES(2, 20, '13:24', 5.0, 0);
INSERT INTO SALE VALUES(3, 5, '13:29', 8.0, 10.0);
INSERT INTO SALE VALUES(4, 3, '13:35', 10.0, 0);
INSERT INTO SALE VALUES(5, 1, '13:40', 5.0, 0);
INSERT INTO SALE VALUES(6, 8, '13:45', 7.5, 5.0);
INSERT INTO SALE VALUES(7, 14, '13:48', 9.25, 3.0);
INSERT INTO SALE VALUES(8, 15, '13:52', 3.0, 5.0);
INSERT INTO SALE VALUES(9, 12, '13:59', 3.0, 0);
INSERT INTO SALE VALUES(10, 11, '14:08', 3.0, 0);
INSERT INTO SALE VALUES(11, 1, '14:21', 5.0, 20.75);
INSERT INTO SALE VALUES(12, 3, '14:25', 3.0, 11);
INSERT INTO SALE VALUES(13, 9, '14:29', 8.0, 4.75);
INSERT INTO SALE VALUES(14, 4, '14:36', 4.75, 5.0);
INSERT INTO SALE VALUES(15, 2, '14:42', 4.5, 0);
INSERT INTO SALE VALUES(16, 11, '14:55', 5.0, 0);
INSERT INTO SALE VALUES(17, 13, '15:12', 5.0, 11.0);
INSERT INTO SALE VALUES(18, 10, '15:48', 9.0, 0);
INSERT INTO SALE VALUES(19, 16, '16:03', 8.25, 3.0);
INSERT INTO SALE VALUES(20, 17, '16:26', 11, 8.0);
INSERT INTO SALE VALUES(21, 18, '16:31', 5.0, 10.0);
INSERT INTO SALE VALUES(22, 19, '16:57', 5.0, 8.25);
INSERT INTO SALE VALUES(23, 1, '17:20', 3.0, 3.0);
INSERT INTO SALE VALUES(24, 20, '17:24', 5.0, 0);
INSERT INTO SALE VALUES(25, 5, '17:29', 8.0, 10.0);
INSERT INTO SALE VALUES(26, 3, '17:35', 10.0, 0);
INSERT INTO SALE VALUES(27, 1, '17:40', 5.0, 0);
INSERT INTO SALE VALUES(28, 8, '17:45', 7.5, 5.0);
INSERT INTO SALE VALUES(29, 14, '17:48', 9.25, 3.0);
INSERT INTO SALE VALUES(30, 15, '17:52', 3.0, 5.0);
INSERT INTO SALE VALUES(31, 12, '17:59', 3.0, 0);
INSERT INTO SALE VALUES(32, 11, '18:08', 3.0, 0);
INSERT INTO SALE VALUES(33, 1, '18:21', 5.0, 20.75);
INSERT INTO SALE VALUES(34, 3, '18:25', 3.0, 11);
INSERT INTO SALE VALUES(35, 9, '18:29', 8.0, 4.75);
INSERT INTO SALE VALUES(36, 4, '18:36', 4.75, 5.0);
INSERT INTO SALE VALUES(37, 2, '18:42', 4.5, 0);
INSERT INTO SALE VALUES(38, 11, '18:55', 5.0, 0);
INSERT INTO SALE VALUES(39, 13, '18:12', 5.0, 11.0);
INSERT INTO SALE VALUES(40, 10, '18:48', 9.0, 0);
INSERT INTO SALE VALUES(41, 16, '18:03', 8.25, 3.0);
INSERT INTO SALE VALUES(42, 17, '18:26', 11, 8.0);
INSERT INTO SALE VALUES(43, 18, '18:31', 5.0, 10.0);
INSERT INTO SALE VALUES(44, 19, '18:57', 9.0, 8.25);
INSERT INTO SALE VALUES(45, 19, '19:05', 5.0, 0);
INSERT INTO SALE VALUES(46, 19, '19:10', 20.0, 5.0);
INSERT INTO SALE VALUES(47, 19, '19:18', 16.75, 4.75);
INSERT INTO SALE VALUES(48, 19, '19:24', 12.5, 15.0);
INSERT INTO SALE VALUES(49, 19, '19:38', 4.75, 0);
INSERT INTO SALE VALUES(50, 19, '19:46', 4.5, 3.0);

-- Offers
INSERT INTO OFFERS VALUES(1,3,1);
INSERT INTO OFFERS VALUES(1,8,3);
INSERT INTO OFFERS VALUES(1,5,2);
INSERT INTO OFFERS VALUES(2,11,3);
INSERT INTO OFFERS VALUES(3,1,3);
INSERT INTO OFFERS VALUES(2,7,1);
INSERT INTO OFFERS VALUES(2,7,2);
INSERT INTO OFFERS VALUES(3,6,2);
INSERT INTO OFFERS VALUES(3,4,1);

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
INSERT INTO RECORDS VALUES(1,1,1);
INSERT INTO RECORDS VALUES(2,2,2);
INSERT INTO RECORDS VALUES(3,3,1);
INSERT INTO RECORDS VALUES(3,2,2);
INSERT INTO RECORDS VALUES(4,1,2);
INSERT INTO RECORDS VALUES(5,2,2);
INSERT INTO RECORDS VALUES(6,3,10);
INSERT INTO RECORDS VALUES(7,2,3);
INSERT INTO RECORDS VALUES(8,1,5);
INSERT INTO RECORDS VALUES(9,2,5);
INSERT INTO RECORDS VALUES(10,3,1);
INSERT INTO RECORDS VALUES(11,2,9);
INSERT INTO RECORDS VALUES(12,1,1);
INSERT INTO RECORDS VALUES(13,2,3);
INSERT INTO RECORDS VALUES(14,3,12);
INSERT INTO RECORDS VALUES(15,2,3);
INSERT INTO RECORDS VALUES(16,1,2);
INSERT INTO RECORDS VALUES(17,2,8);
INSERT INTO RECORDS VALUES(18,3,7);
INSERT INTO RECORDS VALUES(19,2,10);
INSERT INTO RECORDS VALUES(20,1,10);
INSERT INTO RECORDS VALUES(21,2,2);
INSERT INTO RECORDS VALUES(22,3,12);
INSERT INTO RECORDS VALUES(23,1,1);
INSERT INTO RECORDS VALUES(24,2,2);
INSERT INTO RECORDS VALUES(25,3,1);
INSERT INTO RECORDS VALUES(26,2,2);
INSERT INTO RECORDS VALUES(27,1,2);
INSERT INTO RECORDS VALUES(28,2,2);
INSERT INTO RECORDS VALUES(29,3,10);
INSERT INTO RECORDS VALUES(30,2,3);
INSERT INTO RECORDS VALUES(31,1,5);
INSERT INTO RECORDS VALUES(32,2,5);
INSERT INTO RECORDS VALUES(33,3,1);
INSERT INTO RECORDS VALUES(34,2,9);
INSERT INTO RECORDS VALUES(35,1,1);
INSERT INTO RECORDS VALUES(36,2,3);
INSERT INTO RECORDS VALUES(37,3,12);
INSERT INTO RECORDS VALUES(38,2,3);
INSERT INTO RECORDS VALUES(39,1,2);
INSERT INTO RECORDS VALUES(40,2,8);
INSERT INTO RECORDS VALUES(41,3,7);
INSERT INTO RECORDS VALUES(42,2,10);
INSERT INTO RECORDS VALUES(43,1,10);
INSERT INTO RECORDS VALUES(44,2,2);
INSERT INTO RECORDS VALUES(45,3,12);
INSERT INTO RECORDS VALUES(46,2,9);
INSERT INTO RECORDS VALUES(47,1,11);
INSERT INTO RECORDS VALUES(48,2,6);
INSERT INTO RECORDS VALUES(49,3,12);
INSERT INTO RECORDS VALUES(50,2,3);

INSERT INTO CLOCK VALUES('2022-11-26');