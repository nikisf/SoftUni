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
end;

CREATE TABLE notification_emails
  (
     `id`      INT PRIMARY KEY NOT NULL auto_increment,
     recipient INT NOT NULL,
     `subject` VARCHAR(100),
     body      VARCHAR(256)
  );

CREATE TRIGGER `tr_email_trigger` after INSERT
ON `logs`
FOR EACH row
begin
  INSERT INTO `notification_emails`
              (`recipient`,
               `subject`,
               `body`)
  VALUES      (new.account_id,
               Concat('Balance change for account: ', new.account_id),
               Concat('On ', Date_format(Now(), '%b %d %Y at %r'),
               ' your balance was changed from ', new.old_sum, ' to ',
               new.new_sum, '.'));
end;  