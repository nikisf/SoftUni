CREATE function ufn_is_word_comprised(set_of_letters VARCHAR(50),
                                      word           VARCHAR(50))
returns INT
begin
  DECLARE inx INT;

  DECLARE symbol VARCHAR(1);

  SET inx := 1;

  WHILE inx <= Char_length(word) do
    SET symbol = substring(word, inx, 1);
    IF Locate(symbol, set_of_letters) = 0 THEN
      RETURN 0;
    end IF;
    SET inx := inx+1;
  end WHILE;

  RETURN 1;
end;  