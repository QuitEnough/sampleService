INSERT INTO products.details (seller_id, product_id, value) VALUES
('USBL', '100', ('{"name":"Peanut Butter","weight":"10 oz","description":"It is good on bread.","taxonomy_id":"3"}')),
('CABL', '102', ('{"name":"Grape Jelly","weight":"2 oz","description":"It is good with peanut butter.","taxonomy_id":"3"}'));

INSERT INTO products.nutrition (seller_id, product_id, value) VALUES
('USBL', '100', ('{"serving_size":"1 oz","calories":"100","allergens":["nuts","fish"]}')),
('CABL', '102', ('{"serving_size":"2 oz","calories":"150","allergens":["sugar","grape"]}'));

INSERT INTO products.taxonomy (seller_id, taxonomy_id, value) VALUES
('USBL', '3', ('{"L1":"Condiments","L2":"Spreads","L3":"Peanut Butter"}')),
('CABL', '3', ('{"L1":"Condiments","L2":"Jelly/Jam","L3":"Grape"}'));

INSERT INTO products.inventory (seller_id, product_id, value) VALUES
('USBL', '100', ('{"unit_of_measure":"ea","quantity_on_hand":12,"available_on_hand":12,"next_receive_quantity":45}')),
('CABL', '102', ('{"unit_of_measure":"cs","quantity_on_hand":4,"available_on_hand":4,"next_receive_quantity":15}')),
('USBL', '32', ('{"unit_of_measure":"cs","quantity_on_hand":42,"available_on_hand":26,"next_receive_quantity":10}'));

