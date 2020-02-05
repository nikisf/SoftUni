SELECT 
    mc.country_code, COUNT(m.mountain_range) AS mountain_range
FROM
    mountains_countries AS mc
        JOIN
    mountains AS m ON m.id = mc.mountain_id
WHERE
    mc.country_code IN ('BG' , 'RU', 'US')
GROUP BY mc.country_code
ORDER BY mountain_range DESC