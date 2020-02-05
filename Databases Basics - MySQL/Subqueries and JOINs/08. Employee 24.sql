SELECT 
    e.`employee_id`,
    `first_name`,
    IF(YEAR(p.start_date) >= 2005,
        NULL,
        p.`name`) AS project_name
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON ep.employee_id = e.employee_id
        JOIN
    projects AS p ON p.project_id = ep.project_id
WHERE
    e.employee_id = 24
ORDER BY project_name;