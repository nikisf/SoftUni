SELECT 
    *
FROM
    towns
WHERE
    LOWER(`name`) REGEXP '^[mkbe][a-z]+$'
ORDER BY `name`;