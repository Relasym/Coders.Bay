-- 1 create table Create the tables DEPT and EMP from the file dept_emp.sql.

-- 2 simple output 1 Output of all departments (for DNAME column heading DEPARTMENT NAME).
SELECT DNAME FROM DEPT;

-- 3 date output Output of EMPNO, ENAME and HIREDATE (format DD. Month YYYY) for each employee.
SELECT EMPNO,ENAME, TO_CHAR(HIREDATE,'DD. MONTH YYYY')
FROM EMP;

-- 4 date output 2 Output of ENAME and the number of days since joining the company (column heading DAYS) for each employee.
SELECT ENAME, ROUND(current_date - HIREDATE) AS DAYS
FROM EMP;

-- 5 simple output 2 Output of jobs (only 1 output per job).
SELECT DISTINCT JOB
FROM EMP;

-- 6 minmax output Output of the minimum, maximum and average salary.
SELECT ROUND(MIN(SAL)) AS MINIMUM, ROUND(MAX(SAL)) AS MAXIMUM, ROUND(AVG(SAL)) AS AVERAGE
FROM EMP;

-- 7 count 1 Statement to determine "How many employees are there?".
SELECT COUNT(ENAME) AS EMPLOYEES
FROM EMP;

-- 8 count 2 Statement to determine "How many different jobs are there?".
SELECT COUNT(DISTINCT JOB) AS JOBS
FROM EMP