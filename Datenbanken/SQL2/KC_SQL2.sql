--Select

--the HR department wants a query to display the last name, job ID (JOB_ID),
-- hiring date and employee number for each employee,
-- with the employee number as the first value Specify the alias STARTDATE for the HIRE_DATE column. (2 points)
SELECT EMPLOYEE_ID, LAST_NAME, JOB_ID, HIRE_DATE AS STARTDATE
FROM EMPLOYEES;

--the human resources department needs a query to display all unique job identifiers (JOB_ID) from the EMPLOYEES table,
-- Duplicates should be avoided. (2 points)
SELECT DISTINCT JOB_ID
FROM EMPLOYEES;

--For budget purposes, the Human Resources Department requires a report
-- that shows last name and salary for employees earning more than $12,000. Run the query. (2 points)
SELECT LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY > 12000;

--create a report to display the last name and department number for the employee with employee number 176 (3 points)
SELECT LAST_NAME, DEPARTMENT_ID
FROM EMPLOYEES
WHERE EMPLOYEE_ID = 176;

--create a report to show the last name, job ID (JOB_ID) and hiring date of all employees
--Sort the query in ascending order by hiring date. (3 points)
SELECT LAST_NAME, JOB_ID, HIRE_DATE
FROM EMPLOYEES
ORDER BY HIRE_DATE;

--display last names and department numbers of all employees in department 20,
-- sorted alphabetically by last name in ascending order (3 points)
SELECT LAST_NAME, DEPARTMENT_ID
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 20
ORDER BY LAST_NAME;

--create a query that shows last names, salaries and commissions of all employees whose commission is 20%.
-- Give the columns Employee, Monthly Salary, and Commission headings. (3 points)

SELECT LAST_NAME AS Employee, SALARY AS "Monthly Salary", COMMISSION_PCT * 100 AS Commission
FROM EMPLOYEES
WHERE COMMISSION_PCT = 0.2;


--Joins

--The management would like a list of the different salaries per job.
-- The output should contain the job_id as well as the sum of the salaries per job_id.
-- In addition, the output should be sorted in descending order according to the sum of the salaries.
SELECT JOBS.JOB_ID, NVL(SUM(SALARY), 0) AS "TOTAL SALARY"
FROM EMPLOYEES,
     JOBS
WHERE EMPLOYEES.JOB_ID (+) = JOBS.JOB_ID
GROUP BY JOBS.JOB_ID
ORDER BY NVL(SUM(SALARY), 0) DESC;

SELECT JOB_ID,
       NVL((SELECT SUM(SALARY)
            FROM EMPLOYEES
            WHERE EMPLOYEES.JOB_ID = JOBS.JOB_ID
            GROUP BY EMPLOYEES.JOB_ID), 0) AS "TOTAL SALARY"
FROM JOBS
ORDER BY "TOTAL SALARY" DESC;

--The personnel department wants to have information about the average salary of the employees at the current time.
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEES;

--The personnel department would like a list of all employees (first name, last name),
-- on which the department name (department_name) is also displayed.
SELECT FIRST_NAME, LAST_NAME, DEPARTMENT_NAME
FROM EMPLOYEES,
     DEPARTMENTS
WHERE EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID (+);

SELECT FIRST_NAME,
       LAST_NAME,
       (SELECT DEPARTMENT_NAME
        FROM DEPARTMENTS
        WHERE EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID)
FROM EMPLOYEES;


--For the new stationery, the secretary's office needs a list of all departments (department_name)
-- as well as their address consisting of the postal code, the city, the province, and the street and house number
SELECT DEPARTMENT_NAME, POSTAL_CODE, CITY, STATE_PROVINCE, STREET_ADDRESS
FROM DEPARTMENTS,
     LOCATIONS
WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID;

SELECT DEPARTMENT_NAME,
       (SELECT POSTAL_CODE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)    AS POSTAL_CODE,
       (SELECT CITY FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)           AS CITY,
       (SELECT STATE_PROVINCE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STATE_PROVINCE,
       (SELECT STREET_ADDRESS FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STREET_ADDRESS
FROM DEPARTMENTS;


--The secretariat thanks for the list, but would like to have the name of the country in addition.
SELECT DEPARTMENT_NAME, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_NAME, STREET_ADDRESS
FROM DEPARTMENTS,
     LOCATIONS,
     COUNTRIES
WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID
  AND LOCATIONS.COUNTRY_ID = COUNTRIES.COUNTRY_ID;

SELECT DEPARTMENT_NAME,
       (SELECT POSTAL_CODE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)    AS POSTAL_CODE,
       (SELECT CITY FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)           AS CITY,
       (SELECT STATE_PROVINCE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STATE_PROVINCE,
       (SELECT STREET_ADDRESS FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STREET_ADDRESS,
       (SELECT COUNTRY_NAME FROM COUNTRIES WHERE COUNTRY_ID= (SELECT COUNTRY_ID FROM LOCATIONS WHERE LOCATION_ID=DEPARTMENTS.LOCATION_ID)) AS COUNTRY
FROM DEPARTMENTS;


--The secretariat thanks for the updated list. Embarrassed, the first and last name as "Manager"
-- of the respective manager of the department is now requested in addition.
SELECT DEPARTMENT_NAME,
       POSTAL_CODE,
       CITY,
       STATE_PROVINCE,
       COUNTRY_NAME,
       STREET_ADDRESS,
       FIRST_NAME || ' ' || LAST_NAME AS MANAGER
FROM DEPARTMENTS,
     LOCATIONS,
     COUNTRIES,
     EMPLOYEES
WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID
  AND LOCATIONS.COUNTRY_ID = COUNTRIES.COUNTRY_ID
  AND DEPARTMENTS.MANAGER_ID = EMPLOYEES.EMPLOYEE_ID (+);

SELECT DEPARTMENT_NAME,
       (SELECT POSTAL_CODE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)    AS POSTAL_CODE,
       (SELECT CITY FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID)           AS CITY,
       (SELECT STATE_PROVINCE FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STATE_PROVINCE,
       (SELECT STREET_ADDRESS FROM LOCATIONS WHERE DEPARTMENTS.LOCATION_ID = LOCATIONS.LOCATION_ID) AS STREET_ADDRESS,
       (SELECT COUNTRY_NAME FROM COUNTRIES WHERE COUNTRY_ID= (SELECT COUNTRY_ID FROM LOCATIONS WHERE LOCATION_ID=DEPARTMENTS.LOCATION_ID)) AS COUNTRY,
       (SELECT FIRST_NAME||' '||LAST_NAME FROM EMPLOYEES WHERE DEPARTMENTS.MANAGER_ID=EMPLOYEES.EMPLOYEE_ID) AS NAME
FROM DEPARTMENTS;

/*The personnel department needs a list of the employees with the following contents:
1.) First and last name as "Name
2.) job_title as "job"
3.) The salary
4.) The department name */
SELECT FIRST_NAME || ' ' || LAST_NAME AS NAME, JOB_TITLE AS JOB, SALARY, NVL(DEPARTMENT_NAME, 'none') AS DEPARTMENT
FROM EMPLOYEES,
     JOBS,
     DEPARTMENTS
WHERE EMPLOYEES.JOB_ID = JOBS.JOB_ID
  AND EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID (+);

SELECT FIRST_NAME || ' ' || LAST_NAME                                                              AS NAME,
       (SELECT JOB_TITLE FROM JOBS WHERE E.JOB_ID = JOBS.JOB_ID)                                   AS JOB,
       SALARY,
       (SELECT DEPARTMENT_NAME FROM DEPARTMENTS WHERE E.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID) AS DEPARTMENT
FROM EMPLOYEES E;


--The new General Manager asks you to find out which subordinates each employee has.
SELECT E2.FIRST_NAME || ' ' || E2.LAST_NAME               AS EMPLOYEE,
       LISTAGG(E1.FIRST_NAME || ' ' || E1.LAST_NAME, ',') AS SUBORDINATES
FROM EMPLOYEES E1,
     EMPLOYEES E2
WHERE E1.MANAGER_ID = E2.EMPLOYEE_ID (+)
GROUP BY E2.FIRST_NAME || ' ' || E2.LAST_NAME;


SELECT LPAD(LAST_NAME, LENGTH(LAST_NAME)+2*(lEVEL-1),'>')  AS NAME
FROM EMPLOYEES
START WITH MANAGER_ID IS NULL
CONNECT BY PRIOR EMPLOYEE_ID=MANAGER_ID;
