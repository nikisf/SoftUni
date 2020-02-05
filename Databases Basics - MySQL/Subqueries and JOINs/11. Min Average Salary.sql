SELECT 
    MIN(aver)
FROM
    (SELECT 
        AVG(salary) AS aver
    FROM
        employees AS e
    GROUP BY department_id) AS e1