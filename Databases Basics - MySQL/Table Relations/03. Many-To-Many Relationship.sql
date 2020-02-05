CREATE TABLE exams (
    `exam_id` INT PRIMARY KEY UNIQUE,
    `name` VARCHAR(45) NOT NULL
);

INSERT INTO exams
VALUES
(101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

CREATE TABLE students (
    `student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
);

INSERT INTO students
VALUES
(1, 'Mila'),
(2, 'Toni'),
(3, 'Ron');

CREATE TABLE students_exams (
    `student_id` INT,
    `exam_id` INT,
    PRIMARY KEY (`student_id` , `exam_id`),
    CONSTRAINT fk_students_exams_students FOREIGN KEY (`student_id`)
        REFERENCES students (`student_id`),
    CONSTRAINT fk_students_exams_exams FOREIGN KEY (`exam_id`)
        REFERENCES exams (`exam_id`)
);

INSERT INTO students_exams
VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);