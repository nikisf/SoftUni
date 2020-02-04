SELECT 
    department_id,
    (SELECT DISTINCT
            (salary)
        FROM
            employees
        WHERE
            department_id = t1.department_id
        ORDER BY salary DESC
        LIMIT 2 , 1)
FROM
    employees AS t1
WHERE
    (SELECT DISTINCT
            (salary)
        FROM
            employees
        WHERE
            department_id = t1.department_id
        ORDER BY salary DESC
        LIMIT 2 , 1) IS NOT NULL
GROUP BY department_id