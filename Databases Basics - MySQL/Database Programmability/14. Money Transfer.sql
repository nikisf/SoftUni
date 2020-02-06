 CREATE PROCEDURE
  usp_transfer_money(from_account_id INT,
                     to_account_id   INT,
                     amount          DECIMAL(20,4))
begin
  start transaction;
  IF
      (
      SELECT `id`
      FROM   accounts
      WHERE  id = from_account_id) IS NULL THEN
    rollback;
  ELSEIF
          (
          SELECT `id`
          FROM   accounts
          WHERE  id = to_account_id) IS NULL THEN
    rollback;
  ELSEIF
    amount < 0 THEN
    rollback;
  ELSEIF
    amount >
    (
           SELECT `balance`
           FROM   accounts
           WHERE  from_account_id = id) THEN
    rollback;
  ELSEIF
    from_account_id = to_account_id THEN
    rollback;
  ELSE
    UPDATE accounts
    SET    balance = balance - amount
    WHERE  from_account_id = id;
    
    UPDATE accounts
    SET    balance = balance + amount
    WHERE  to_account_id = id;
  
  end IF;
end; 