 CREATE PROCEDURE
  usp_deposit_money(account_id   INT,
                    money_amount DECIMAL(20,4))
begin
  start transaction;
  IF money_amount <= 0 THEN
    rollback;
  ELSE
    UPDATE accounts
    SET    balance = balance + money_amount
    WHERE  account_id = id;
  
  end IF;
end 