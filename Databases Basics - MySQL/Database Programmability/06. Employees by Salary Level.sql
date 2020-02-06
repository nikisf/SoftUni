CREATE function ufn_get_salary_level (emp_salary DECIMAL)
returns VARCHAR(10)
begin
  DECLARE result VARCHAR(10);

  IF emp_salary < 30000 THEN
    SET result := 'low';
  ELSEIF emp_salary BETWEEN 30000 AND 50000 THEN
    SET result := 'average';
  ELSE
    SET result := 'high';
  end IF;

  RETURN result;
end;

CREATE PROCEDURE usp_get_employees_by_salary_level (salary_level VARCHAR(10))
begin
  SELECT `first_name`,
         `last_name`
  FROM   employees
  WHERE  Ufn_get_salary_level(salary) = salary_level
  ORDER  BY `first_name` DESC,
            `last_name` DESC;
end  