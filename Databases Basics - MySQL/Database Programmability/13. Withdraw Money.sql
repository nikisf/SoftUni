 CREATE PROCEDURE
  usp_withdraw_money(account_id   INT,
                     money_amount DECIMAL(20,4))
begin
  start transaction;
  IF money_amount <= 0 THEN
    rollback;
  ELSEIF
    money_amount >
    (
           SELECT `balance`
           FROM   accounts
           WHERE  account_id = id) THEN
    rollback;
  ELSE
    UPDATE accounts
    SET    balance = balance - money_amount
    WHERE  account_id = id;
  
  end IF;
end; 