select `country_name`, `iso_code` from countries
where lower(`country_name`) LIKE '%a%a%a%'
order by `iso_code`