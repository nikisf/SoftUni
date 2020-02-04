CREATE TABLE `directors`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`director_name` VARCHAR(200) NOT NULL,
`notes` VARCHAR(255)
);

CREATE TABLE `genres`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`genre_name` VARCHAR(200) NOT NULL,
`notes` VARCHAR(255)
);

CREATE TABLE `categories` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`category_name` VARCHAR(200) NOT NULL,
`notes` VARCHAR(255)
);

CREATE TABLE `movies` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`title` VARCHAR(100) NOT NULL,
`director_id` INT,
`copyright_year` YEAR,
`length` TIME,
`genre_id` INT,
`category_id` INT,
`rating` DOUBLE(3,2),
`notes` VARCHAR(255),

CONSTRAINT fk_movies_directors
FOREIGN KEY `movies`(`director_id`)
REFERENCES `directors`(`id`),

CONSTRAINT fk_movies_genres
FOREIGN KEY `movies`(`genre_id`)
REFERENCES `genres`(`id`),

CONSTRAINT fk_movies_categories
FOREIGN KEY `movies`(`category_id`)
REFERENCES `categories`(`id`)
);

INSERT INTO `directors`(
`id`, `director_name`, `notes`
)
VALUES
(1, 'pesho', null),
(2, 'dao', null),
(3, 'www', null),
(4, 'aaa', null),
(5, 'sss', null);

INSERT INTO `genres`(
`id`, `genre_name`, `notes`
)
VALUES
(1, 'qqq', null),
(2, 'www', null),
(3, 'eee', null),
(4, 'rrr', null),
(5, 'ttt', null);

INSERT INTO `categories`(
`id`, `category_name`, `notes`
)
VALUES
(1, 'z', null),
(2, 's', null),
(3, 'd', null),
(4, 'f', null),
(5, 'g', null);

INSERT INTO `movies`(
`id`, `title`, `director_id`, `copyright_year`, `length`, `genre_id`, `category_id`, `rating`, `notes`
)
VALUES
(1, 'zag1', '1', "1990", null, '1', '1', null, null),
(2, 'zag2', '2', "1991", null, '2', '2', null, null),
(3, 'zag3', '1', "1992", null, '3', '3', null, null),
(4, 'zag4', '1', "1993", null, '4', '4', null, null),
(5, 'zag5', '1', "1994", null, '5', '5', null, null);