SELECT 
    `mountain_range`, b1.peak_name, b1.elevation
FROM
    mountains AS m1
        JOIN
    peaks AS b1 ON m1.id = b1.mountain_id
WHERE
    m1.mountain_range = 'Rila'
ORDER BY b1.elevation DESC