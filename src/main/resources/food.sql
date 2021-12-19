  CREATE TABLE mem_food(
id INT Primary key AUTO_INCREMENT,
food_name VARCHAR(255) ,
food_price DEC(4,2) ,
food_type VARCHAR(255),
food_quantity DEC(4,2)
);

INSERT INTO mem_food (food_name, food_price, food_type) VALUES 
('Tomatoes', 2.97, 'veg'),
('spinach', 5.57, 'veg'),
('carrots', 3.77, 'veg'),
('broccoli', 2.67, 'veg'),
('strawberries', 4.57, 'fruit'),
('blueberries', 4.47, 'fruit'),
('raspberries', 4.17, 'fruit'),
('Chicken Breast', 7.97, 'meat'),
('Chicken Wings', 12.97, 'meat'),
('Chiceken Strips', 8.97, 'meat'),
('Chicken Drums', 5.97, 'meat');