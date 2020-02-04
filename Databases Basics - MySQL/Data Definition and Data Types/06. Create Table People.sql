CREATE TABLE `people` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `picture` BLOB NULL,
  `height` DECIMAL(5,2) NULL,
  `weight` DECIMAL(5,2) NULL,
  `gender` CHAR NOT NULL,
  `birthdate` DATE NOT NULL,
  `biography` VARCHAR(264) NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`) VALUES ('1', 'ddsa', 'm', '1990-12-23');
INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`) VALUES ('2', '32131', 'f', '1990-11-21');
INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`) VALUES ('3', 'ewqewq', 'm', '1990-11-21');
INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`) VALUES ('4', 'eqeq', 'm', '1997-11-22');
INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`) VALUES ('5', 'eqw', 'm', '1999-01-01');