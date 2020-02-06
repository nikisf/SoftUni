CREATE PROCEDURE usp_get_employees_salary_above(salary_number DOUBLE)
BEGIN
SELECT `first_name`, `last_name` FROM employees
WHERE salary >= salary_number
 ORDER BY first_name, last_name;
END