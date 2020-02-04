CREATE TABLE newTable SELECT * FROM
    employees
WHERE
    salary > 30000;
DELETE FROM newTable 
WHERE
    manager_id = 42;
UPDATE newTable 
SET 
    salary = salary + 5000
WHERE
    department_id = 1;
SELECT 
    department_id, AVG(salary)
FROM
    newTable
GROUP BY department_id;