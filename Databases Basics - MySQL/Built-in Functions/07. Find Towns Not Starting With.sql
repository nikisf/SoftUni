SELECT 
    *
FROM
    towns
WHERE
    LOWER(`name`) REGEXP '^[^rbd][a-z]+$'
ORDER BY `name`;