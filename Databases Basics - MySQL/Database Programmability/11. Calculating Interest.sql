CREATE function ufn_calculate_future_value (sum                  DOUBLE,
                                            yearly_interest_rate DOUBLE,
                                            number_of_years      INT)
returns DECIMAL(10, 4)
begin
  DECLARE result DOUBLE;

  SET result = sum;

  WHILE number_of_years >= 1 do
    SET result := result + (result * yearly_interest_rate);
    SET number_of_years := number_of_years - 1;
  end WHILE;

  RETURN result;
end;

CREATE PROCEDURE usp_calculate_future_value_for_account(account_id    INT,
                                                        interest_rate DECIMAL(10
, 4))
begin
  SELECT a.`id`,
         ah.`first_name`,
         ah.`last_name`,
         a.`balance`                                               AS
               account_balance,
         Ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS
               balance_in_5_years
  FROM   account_holders AS ah
         JOIN accounts AS a
           ON a.account_holder_id = ah.id
  WHERE  account_id = a.id;
end;  