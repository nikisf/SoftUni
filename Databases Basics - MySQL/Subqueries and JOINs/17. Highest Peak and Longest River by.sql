SELECT 
    `country_name`,
    MAX(p.elevation) AS highest_peak_elevation,
    MAX(r.length) AS longest_river_length
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON mc.country_code = c.country_code
        JOIN
    mountains AS m ON m.id = mc.mountain_id
        JOIN
    countries_rivers AS cr ON cr.country_code = c.country_code
        JOIN
    rivers AS r ON r.id = cr.river_id
        JOIN
    peaks AS p ON p.mountain_id = m.id
GROUP BY country_name
ORDER BY highest_peak_elevation DESC , longest_river_length DESC , country_name
LIMIT 5