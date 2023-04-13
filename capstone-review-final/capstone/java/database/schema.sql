BEGIN TRANSACTION;

DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS menu_item_cuisine;
DROP TABLE IF EXISTS cuisine;
DROP TABLE IF EXISTS order_menu_item;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS submenu;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_food_truck_favorites;
DROP TABLE IF EXISTS trucks;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE trucks(
	truck_id SERIAL PRIMARY KEY,
	active boolean,
	accepted_payment varchar(25),
	cuisine_id int,
	restaurant_link varchar(100),
	phone varchar(25),
	current_location varchar(100)
);

CREATE TABLE user_food_truck_favorites(
	user_id int REFERENCES users(user_id),
	truck_id int REFERENCES trucks(truck_id),
	CONSTRAINT pk_user_food_truck_favorites PRIMARY KEY (user_id, truck_id)
);

CREATE TABLE orders(
	order_id SERIAL PRIMARY KEY,
	favorited boolean,
	truck_id int REFERENCES trucks(truck_id),
	user_id int REFERENCES users(user_id),
	pickup_date_time TIMESTAMP
);

CREATE TABLE menu(
	menu_id SERIAL PRIMARY KEY,
	truck_id int REFERENCES trucks(truck_id),
	hours_available varchar(25),
	active boolean,
	menu_name varchar(25)
);

CREATE TABLE submenu(
	submenu_id SERIAL PRIMARY KEY,
	menu_id int REFERENCES menu(menu_id),
	submenu_name varchar(25)
);

CREATE TABLE menu_items(
	menu_item_id SERIAL PRIMARY KEY,
	submenu_id int REFERENCES submenu(submenu_id),
	active boolean,
	price money,
	menu_item_name varchar(25),
	description varchar(150)
);

CREATE TABLE order_menu_item(
	order_id int REFERENCES orders(order_id),
	menu_item_id int REFERENCES menu_items(menu_item_id),
	notes varchar(25),
	CONSTRAINT pk_order_menu_item PRIMARY KEY (order_id, menu_item_id)
);

CREATE TABLE cuisine(
	cuisine_id SERIAL PRIMARY KEY,
	cuisine_name varchar(50)
);

CREATE TABLE menu_item_cuisine(
	menu_item_id int REFERENCES menu_items(menu_item_id),
	cuisine_id int REFERENCES cuisine(cuisine_id),
	CONSTRAINT pk_menu_item_cuisine PRIMARY KEY (menu_item_id, cuisine_id)
);

CREATE TABLE locations(
	location_id SERIAL PRIMARY KEY,
	seating boolean,
	parking boolean,
	location_name varchar(50)
);

CREATE TABLE events(
	event_id SERIAL PRIMARY KEY,
	event_name varchar(50),
	event_date DATE,
	event_start TIMESTAMP,
	event_end TIMESTAMP,
	truck_id int REFERENCES trucks(truck_id),
	location_id int REFERENCES locations(location_id)
);

COMMIT TRANSACTION;
