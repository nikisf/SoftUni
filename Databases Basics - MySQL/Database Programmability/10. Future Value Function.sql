create function ufn_calculate_future_value (sum double, yearly_interest_rate double, number_of_years int)
returns double
begin
declare result double;
set result = sum;
while number_of_years >= 1 do
set result := result + (result * yearly_interest_rate);
set number_of_years := number_of_years - 1 ;
end while;
return result;
end;