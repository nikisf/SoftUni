CREATE PROCEDURE usp_get_towns_starting_with (asdf VARCHAR(30))
BEGIN
SELECT `name` FROM towns
WHERE `name` LIKE concat(asdf , '%')
ORDER BY `name`;
END