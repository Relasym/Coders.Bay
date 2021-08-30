--1-7 Tennis queries

  --1  output of PLAYERNO, NAME of players born after 1960.
SELECT PLAYERNO,NAME
FROM PLAYERS
WHERE YEAR_OF_BIRTH>1960;

  --2  output of PLAYERNO, NAME and TOWN of all female players who do not reside in Strat- ford.
SELECT PLAYERNO, NAME, TOWN
FROM PLAYERS
WHERE SEX='F' AND NOT TOWN='Stratford';

  --3  output of player numbers of players who joined the club between 1970 and 1980.
SELECT PLAYERNO
FROM PLAYERS
WHERE YEAR_JOINED>=1970 AND YEAR_JOINED<=1980;

  --4  output of PlayerId, Name, Year of Birth of players born in a leap year.
SELECT PLAYERNO, NAME, YEAR_OF_BIRTH
FROM PLAYERS
WHERE MOD(YEAR_OF_BIRTH,4)=0 AND NOT MOD(YEAR_OF_BIRTH,100)=0;

  --5  output of the penalty numbers of the penalties between 50,- and 100,-.
SELECT PAYMENTNO
FROM PENALTIES
WHERE AMOUNT BETWEEN 50 and 100;

  --6  output of PlayerId, name of players who do not live in Stratford or Douglas.
SELECT PLAYERNO, NAME
FROM PLAYERS
WHERE NOT (TOWN='Stratford' OR TOWN='Douglas');


  --7  output of playerId and name of players whose name contains 'is'.
SELECT PLAYERNO, NAME
FROM PLAYERS
WHERE NAME LIKE '%is%';

   --8 output of all hobby players.
SELECT PLAYERNO, NAME
FROM PLAYERS
WHERE LEAGUENO IS NULL;

--9 - 21 EmpDept queries.

   --9 output of those employees who receive more commission than salary.
SELECT EMPNO, ENAME
FROM EMP
WHERE COMM>SAL;

   --10 output of all employees from department 30 whose salary is greater than or equal to 1500.
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO=30 AND SAL>=1500;

   --11 output of all managers who do not belong to department 30.
SELECT EMPNO, ENAME
FROM EMP
WHERE JOB='MANAGER' AND DEPTNO!=30;

   --12 output of all employees from department 10 who are neither managers nor clerical workers (CLERK).
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO=10 AND JOB!='MANAGER' AND JOB!='CLERK';

   --13 output of all employees who earn between 1200,- and 1300,-.
SELECT EMPNO, ENAME
FROM EMP
WHERE SAL BETWEEN 1200 and 1300;

   --14 output all employees whose name is 5 characters long and begins with ALL.
SELECT EMPNO, ENAME
FROM EMP
WHERE LENGTH(ENAME)=5 AND ENAME LIKE 'ALL%';

   --15 output the total salary (salary + commission) for each employee.
SELECT EMPNO, ENAME, (CASE WHEN COMM IS NULL THEN SAL ELSE SAL+COMM END) AS "Total Salary"
FROM EMP;

SELECT EMPNO, ENAME, NVL(SAL,0) + NVL(COMM,0) as "Total Salary"
FROM EMP;

   --16 output all employees, whose commission is over 25% of the salary.
SELECT EMPNO, ENAME
FROM EMP
WHERE COMM>0.25*SAL;

   --17 searched is the average salary of all office employees.
SELECT TRUNC(AVG(SAL))
FROM EMP;

   --18 searched is the number of employees who have received a commission.
SELECT COUNT(EMPNO)
FROM EMP
WHERE COMM IS NOT NULL;

   --19 wanted is the number of different jobs in department 30.
SELECT COUNT(DISTINCT JOB)
FROM EMP
WHERE DEPTNO=30;

   --20 wanted is the number of employees in department 30.
SELECT COUNT(EMPNO)
FROM EMP
WHERE DEPTNO=30;

   --21 output of employees hired between 4/1/81 and 15/4/81.
SELECT EMPNO, ENAME
FROM EMP
WHERE HIREDATE BETWEEN TO_DATE('1981-01-04','YYYY-MM-DD') AND TO_DATE('1981-04-15','YYYY-MM-DD');
