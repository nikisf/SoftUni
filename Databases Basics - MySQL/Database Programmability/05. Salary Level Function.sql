CREATE function ufn_get_salary_level (emp_salary DECIMAL)
returns VARCHAR(10)
begin
  DECLARE result VARCHAR(10);

  IF emp_salary < 30000 THEN
    SET result := 'Low';
  ELSEIF emp_salary BETWEEN 30000 AND 50000 THEN
    SET result := 'Average';
  ELSE
    SET result := 'High';
  end IF;

  RETURN result;
end  