CREATE TABLE `towns` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);
create table `addresses` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`address_text` varchar(255) not null,
`town_id` INT,
CONSTRAINT fk_addresses_towns
FOREIGN KEY `addresses`(`town_id`)
REFERENCES `towns`(`id`)
);
create table `departments` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) not null
);
create table `employees`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45),
`middle_name` VARCHAR(45),
`last_name` VARCHAR(45),
`job_title` VARCHAR(45),
`department_id` INT,
`hire_date` DATE,
`salary` DECIMAL(10,2),
`address_id` INT,
CONSTRAINT fk_employees_departments
FOREIGN KEY `employees`(`department_id`)
REFERENCES `departments`(`id`),
CONSTRAINT fk_employees_addresses
FOREIGN KEY `employees`(`address_id`)
REFERENCES `addresses`(`id`)
);