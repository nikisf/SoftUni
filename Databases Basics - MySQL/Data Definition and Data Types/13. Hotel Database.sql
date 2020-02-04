create table `employees`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(200) NOT NULL,
`last_name` VARCHAR(200) NOT NULL,
`title` VARCHAR(100),
`notes` text
);
insert into `employees`(`first_name`, `last_name`) values('asd','dd'),('dd', 'asd'),('aaa','sss');

create table `customers`(
`account_number` VARCHAR(10) NOT NULL,
`first_name` VARCHAR(200) NOT NULL,
`last_name` VARCHAR(200) NOT NULL,
`phone_number` VARCHAR(15),
`emergency_name` VARCHAR(50),
`emergency_number` VARCHAR(15),
`notes` text
);
insert into `customers`(`account_number`, `first_name`, `last_name`)values('eqweqw', 'eqweqw','ewq'),('ewqeqw','eqweqw','ewqeqw'),('eweqw','ewqeqw','eqweqw');

create table `room_status`(
`room_status` VARCHAR(50) NOT NULL,
`notes` text
);

insert into `room_status`(`room_status`)values('eee'),('ddd'),("dda");

create table `room_types`(
`room_type` INT not null,
`notes` TEXT,
primary key(`room_type`)
);
insert into `room_types`(`room_type`)values(1),(2),(3);

create table `bed_types`(
`bed_type` INT not null,
`notes` TEXT,
primary key(`bed_type`)
);
insert into `bed_types`(`bed_type`)values(1),(2),(3);

create table `rooms`(
`room_number` int not null,
`room_type` varchar(50),
`bed_type` varchar(50),
`rate` varchar(50),
`room_status` int not null,
`notes` TEXT,
primary key(`room_number`)
);
insert into `rooms`(`room_number`, `room_status`)values(1, 2),(2, 3),(3, 4);

create table `payments`(
`id` INT not null,
`employee_id` int not null,
`payment_date` date,
`account_number` varchar(10) not null,
`first_date_occupied` date,
`last_date_occupied` date,
`total_days` INT,
`amount_charged` double,
`tax_rate` double,
`tax_amount` double,
`payment_total` double,
`notes` TEXT,
primary key(`id`)
);
insert into `payments`(`id`, `employee_id`, `account_number` )values(1, 22, 'sasas'),(2, 33, 'sasas'),(3, 44, 'sasasa');

create table `occupancies`(
`id` int not null,
`employee_id` int not null ,
`date_occupied` datetime,
`account_number` varchar(10),
`room_number` int not null,
`rate_applied` varchar(10),
`phone_charge` varchar(10),
`notes` TEXT,
primary key(`id`)
);
insert into `occupancies`(`id`,`employee_id`, `room_number` )values(1, 2, 3),(2, 2, 3),(3, 2, 3)