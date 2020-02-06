CREATE TABLE `logs`
  (
     log_id     INT PRIMARY KEY NOT NULL auto_increment,
     account_id INT NOT NULL,
     old_sum    DECIMAL(20, 4) NOT NULL,
     new_sum    DECIMAL(20, 4) NOT NULL
  );

CREATE TRIGGER `tr_balance_update` after UPDATE
ON `accounts`
FOR EACH row
begin
  IF old.balance <> new.balance THEN
    INSERT INTO `logs`
                (account_id,
                 old_sum,
                 new_sum)
    VALUES      (old.id,
                 old.balance,
                 new.balance);
  end IF;
end  