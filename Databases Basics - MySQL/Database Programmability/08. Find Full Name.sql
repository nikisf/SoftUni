CREATE PROCEDURE usp_get_holders_full_name()
begin
  SELECT Concat(`first_name`, ' ', `last_name`) AS full_name
  FROM   account_holders
  ORDER  BY full_name;
end  