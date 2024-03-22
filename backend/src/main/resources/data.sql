-- Sample Client data
INSERT INTO client (id, email,  password, role) VALUES (nextval('client_seq'), 'italianofoodlover@email.com','passwordo', 'ROLE_CUSTOMER');
INSERT INTO client (id, email,  password, role) VALUES (nextval('client_seq'), 'mexicanatacolover@meximail.com','elpasswordo','ROLE_CUSTOMER');
INSERT INTO client (id, email,  password, role) VALUES (nextval('client_seq'), 'mex@meximail.com','elpasswordo1','ROLE_RESTAURANT');
INSERT INTO client (id, email,  password, role) VALUES (nextval('client_seq'), 'randomo@meximail.com','elpasswordo2','ROLE_RESTAURANT');


-- Sample Customer data
INSERT INTO customer (id, public_id,  first_name, last_name, phone_number, client_id) VALUES (nextval('customer_seq'), '74e1a446-7d7a-4b60-8909-5fa2514a7cf7', 'John', 'Doe', '1234567890',1);
INSERT INTO customer (id, public_id,  first_name, last_name, phone_number, client_id) VALUES (nextval('customer_seq'), 'a4999a0c-0aae-49a0-b31c-4dc972c26f7c', 'Jane', 'Smith', '9876543210',2);

-- Sample Restaurant data
INSERT INTO restaurant (id, public_id, name,  phone_number, address, client_id) VALUES (nextval('restaurant_seq'), '2b6d71e5-07e6-4b0f-9022-2ee70bc68264', 'Italiano Ristorante',   '+1234567890', '123 Main St', 3);
INSERT INTO restaurant (id, public_id, name,  phone_number, address, client_id) VALUES (nextval('restaurant_seq'), 'e40856d1-3152-4e65-bbe8-c3f0a1db445b', 'Mexicana Cantina',   '+9876543210', '456 Oak St', 4);

-- Sample Table data
INSERT INTO dining_spot (id, public_id, capacity, name, restaurant_id) VALUES (nextval('dining_seq'), 'efc16827-df20-4fd1-86a7-9bb0a66deba4', 4, 'Table 1', 1);
INSERT INTO dining_spot (id, public_id, capacity, name, restaurant_id) VALUES (nextval('dining_seq'), 'b06b4f1d-6d6e-4576-9a97-5507c4b2a24a', 6, 'Table 2', 2);

-- Sample Cuisine data
INSERT INTO cuisine (id, cuisine_type) VALUES (nextval('cuisine_seq'), 'Italian');
INSERT INTO cuisine (id, cuisine_type) VALUES (nextval('cuisine_seq'), 'Mexican');

-- Sample Reservation data
INSERT INTO reservation (id, public_id, start, duration, number_of_customers, customer_id, dining_spot_id) VALUES (nextval('reservation_seq'), '7df4b9a8-38d5-4841-bb9a-2d892b15e43b', '2024-03-06 18:00:00', 1, 4, 1, 1);
INSERT INTO reservation (id, public_id, start, duration, number_of_customers, customer_id, dining_spot_id) VALUES (nextval('reservation_seq'), 'a8d3c07f-5748-4e16-943b-31a4d72ff1d4', '2024-03-07 19:00:00', 1, 6, 2, 2);

INSERT INTO restaurant_cuisine (cuisine_id, restaurant_id) VALUES (1, 1);
INSERT INTO restaurant_cuisine (cuisine_id, restaurant_id) VALUES (2, 2);
INSERT INTO restaurant_cuisine (cuisine_id, restaurant_id) VALUES (1, 2);
