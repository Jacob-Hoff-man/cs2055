---------------------------------------------
-- CoffeeBoutique CS 1555/2055 Fall 2022
-- Jacob Hoffman and Kairuo Yan
-------------------------------------------
-- Stores
INSERT INTO STORE VALUES (1, 'CB1', 20.1, 50.2, 'kiosk');
INSERT INTO STORE VALUES (2, 'CB2', 50.1, 100.2, 'kiosk');
INSERT INTO STORE VALUES (3, 'CB3', 10.1, 25.2, 'sitting store');

-- Customers
INSERT INTO CUSTOMER VALUES (1, 'Jacob', 'Hof', 'Andrew', 'jul', '08', '111-222-3333', 'mobile', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (2, 'Kairuo', 'Yan', 'Kairuo', 'jan', '01', '222-222-3333', 'home', 350.0, 1500.00, 'platinum');
INSERT INTO CUSTOMER VALUES (3, 'Bob', 'Ross', 'Kyle', 'feb', '02', '333-222-3333', 'work', 500.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (4, 'Luke', 'Skywalker', 'Anakin', 'mar', '03', '444-222-3333', 'other', 10.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (5, 'Mike', 'Trout', 'Ball', 'apr', '04', '555-222-3333', 'mobile', 50.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (6, 'Rock', 'Paper', 'Scissor', 'may', '05', '666-222-3333', 'home', 0.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (7, 'Jason', 'Freddy', 'Michael', 'jun', '06', '777-222-3333', 'work', 600.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (8, 'Count', 'Drake', 'Vlad', 'jul', '07', '888-222-3333', 'other', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (9, 'Bruce', 'Wayne', 'Batman', 'aug', '08', '999-222-3333', 'mobile', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (10, 'Alex', 'Hero', 'Ed', 'sep', '09', '123-222-3333', 'home', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (11, 'Ziggy', 'Swifty', 'Twisty', 'oct', '10', '456-222-3333', 'work', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (12, 'CJ', 'Smith', 'Andrew', 'nov', '11', '789-222-3333', 'other', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (13, 'Jake', 'Hof', 'Andrew', 'dec', '12', '111-333-4444', 'mobile', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (14, 'Zach', 'Blaze', 'Jack', 'jan', '13', '222-333-4444', 'home', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (15, 'Devin', 'Eleven', 'Melvin', 'feb', '14', '333-333-4444', 'work', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (16, 'Rick', 'Morty', 'Slick', 'mar', '15', '444-333-4444', 'other', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (17, 'Joe', 'Average', 'Andrew', 'apr', '16', '555-333-4444', 'mobile', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (18, 'Panos', 'Crythanthis', 'Andrew', 'may', '17', '666-333-4444', 'home', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (19, 'Brian', 'Nixon', 'James', 'jun', '18', '777-333-4444', 'work', 250.0, 1000.00, 'gold');
INSERT INTO CUSTOMER VALUES (20, 'Ralph', 'Lauren', 'Polo', 'jul', '19', '888-333-4444', 'other', 250.0, 1000.00, 'gold');

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
INSERT INTO COFFEE VALUES (12, 'Iced Coffee', 'Iced coffees become very popular in the summertime in the United States. The recipes do have some variance, with some locations choosing to interchange milk with water in the recipe. Often, different flavoring syrups will be added per the preference of the customer.', 'Canada', 3, 4.75, 142.5, 47.50);

-- Promos
INSERT INTO PROMOTION VALUES (1, 'Promo 1', '2022-10-01', '2022-11-01');
INSERT INTO PROMOTION VALUES (1, 'Promo 2', '2021-10-01', '2023-12-30');
INSERT INTO PROMOTION VALUES (1, 'Promo 3', '2023-01-01', '2023-07-01');

-- Purchases