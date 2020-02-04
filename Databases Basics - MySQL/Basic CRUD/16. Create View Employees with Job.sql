 CREATE VIEW v_employees_job_titles AS
 SELECT concat_ws(' ', `first_name`,ifnull(`middle_name`,''), `last_name`) as `full_name`, `job_title` from employees;
 SELECT 
    *
FROM
    v_employees_job_titles;