CREATE TABLE passports (
    `passport_id` INT PRIMARY KEY,
    `passport_number` VARCHAR(100) UNIQUE
);

CREATE TABLE persons (
    `person_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(10 , 2 ) NOT NULL,
    `passport_id` INT UNIQUE,
    CONSTRAINT fk_persons_passports FOREIGN KEY (`passport_id`)
        REFERENCES passports (`passport_id`)
);

INSERT INTO passports
VALUES
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

INSERT INTO persons
VALUES
(1, 'Roberto', 43300.00, 102),
(2, 'Tom', 56100.00, 103),
(3, 'Yana', 60200.00, 101);