CREATE TABLE `categories`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`category` VARCHAR(100) NOT NULL,
`daily_rate` DOUBLE(3,2),
`weekly_rate` DOUBLE(3,2),
`monthly_rate` DOUBLE(3,2),
`weekend_rate` DOUBLE(3,2)
);

CREATE TABLE `cars`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`plate_number` INT NOT NULL,
`make` VARCHAR(50) NOT NULL,
`model` VARCHAR(50) NOT NULL,
`car_year` YEAR,
`category_id` INT,
`doors` INT,
`picture` BLOB,
`car_condition` VARCHAR(200),
`available` BOOLEAN,

CONSTRAINT fk_cars_categories
FOREIGN KEY `cars`(`category_id`)
REFERENCES `categories`(`id`)
);

CREATE TABLE `employees`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(200) NOT NULL,
`last_name` VARCHAR(200) NOT NULL,
`title` VARCHAR(100),
`notes` VARCHAR(255) 
);

CREATE TABLE `customers`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`driver_licence_number` INT NOT NULL,
`full_name` VARCHAR(255) NOT NULL,
`address` VARCHAR(200) NOT NULL,
`city` VARCHAR(50) NOT NULL,
`zip_code` INT NOT NULL,
`notes` VARCHAR(255)
);

CREATE TABLE `rental_orders`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`employee_id` INT,
`customer_id` INT,
`car_id` INT,
`car_condition` VARCHAR(200),
`tank_level` INT,
`kilometrage_start` INT,
`kilometrage_end` INT,
`total_kilometrage` INT,
`start_date` DATE,
`end_date` DATE,
`total_days` INT,
`rate_applied` INT,
`tax_rate` INT,
`order_status` VARCHAR(100),
`notes` VARCHAR(255),

CONSTRAINT fk_rental_orders_employees
FOREIGN KEY `rental_orders`(`employee_id`)
REFERENCES `employees`(`id`),

CONSTRAINT fk_rental_orders_customers
FOREIGN KEY `rental_orders`(`customer_id`)
REFERENCES `customers`(`id`),

CONSTRAINT fk_rental_orders_cars
FOREIGN KEY `rental_orders`(`car_id`)
REFERENCES `cars`(`id`)
);

INSERT INTO `categories`
(`id`,
`category`,
`daily_rate`,
`weekly_rate`,
`monthly_rate`,
`weekend_rate`)
VALUES
(1, 'ASD', null, null, null, null),
(2, 'ddd', null, null, null, null),
(3, 'sss', null, null, null, null);

INSERT INTO `cars`
(`id`,
`plate_number`,
`make`,
`model`,
`car_year`,
`category_id`,
`doors`,
`picture`,
`car_condition`,
`available`)
VALUES
(1, '333', 'aaa', 'aaa1', null, '1', null, null, null, null),
(2, '222', 'bbb', 'bbb1', null, '2', null, null, null, null),
(3, '111', 'ccc', 'ccc1', null, '3', null, null, null, null);

INSERT INTO `employees`
(`id`,
`first_name`,
`last_name`,
`title`,
`notes`)
VALUES
(1, 'asd', 'asd', null, null),
(2, 'zzz', 'ddd', null, null),
(3, 'sss', 'aaa', null, null);

INSERT INTO `customers`
(`id`,
`driver_licence_number`,
`full_name`,
`address`,
`city`,
`zip_code`,
`notes`)
VALUES
(1, '111', 'asd asd', 'tam', 'tuka', '111', null),
(2, '222', 'qqq qqq', 'aaa', 'fff', '111', null),
(3, '333', 'aaa aaa', 'sss', 'ddd', '111', null);

INSERT INTO `rental_orders`
(`id`,
`employee_id`,
`customer_id`,
`car_id`,
`car_condition`,
`tank_level`,
`kilometrage_start`,
`kilometrage_end`,
`total_kilometrage`,
`start_date`,
`end_date`,
`total_days`,
`rate_applied`,
`tax_rate`,
`order_status`,
`notes`)
VALUES
(1, '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null),
(2, '2', '2', '2', null, null, null, null, null, null, null, null, null, null, null, null),
(3, '3', '3', '3', null, null, null, null, null, null, null, null, null, null, null, null);