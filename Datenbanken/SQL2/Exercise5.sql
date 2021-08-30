--Exercise 5
--1-11 Tennis query

--    number of new players per year
SELECT YEAR_JOINED, COUNT(PLAYERNO)
FROM PLAYERS
GROUP BY YEAR_JOINED;

--    number and average amount of penalties per player
SELECT PLAYERNO, COUNT(PAYMENTNO) as Penalties, TRUNC(AVG(AMOUNT)) "Average Penalty"
FROM PENALTIES
GROUP BY PLAYERNO;

--    number of penalties for the years before 1983
SELECT COUNT(PAYMENTNO)
FROM PENALTIES
WHERE EXTRACT(year from PEN_DATE) < 1983;

--    in which cities live more than 4 players
SELECT TOWN
FROM PLAYERS
GROUP BY TOWN
HAVING COUNT(PLAYERNO) > 4;

--    PLAYERNO of those players whose penalty total is over 150
SELECT PLAYERNO
FROM PENALTIES
GROUP BY PLAYERNO
HAVING SUM(AMOUNT) > 150;

--    NAME and INITIALS of those players who received more than one penalty
SELECT INITIALS, NAME
FROM PLAYERS
WHERE PLAYERNO IN (SELECT PLAYERNO FROM PENALTIES GROUP BY PLAYERNO HAVING COUNT(PAYMENTNO) > 1);

--    in which years there were exactly 2 penalties
SELECT EXTRACT(YEAR FROM PEN_DATE) as "Year", COUNT(PEN_DATE)
FROM PENALTIES
GROUP BY EXTRACT(YEAR FROM PEN_DATE)
HAVING COUNT(EXTRACT(YEAR FROM PEN_DATE)) = 2;

--    NAME and INITIALS of the players who received 2 or more penalties over $40
SELECT NAME, INITIALS
FROM PLAYERS
WHERE PLAYERNO IN
      (SELECT PLAYERNO FROM (SELECT * FROM PENALTIES WHERE AMOUNT > 50) GROUP BY PLAYERNO HAVING COUNT(PAYMENTNO) >= 2);

--    NAME and INITIALS of the player with the highest penalty amount
SELECT NAME, INITIALS
FROM PLAYERS
WHERE PLAYERNO IN
      (SELECT PLAYERNO FROM PENALTIES GROUP BY PLAYERNO, AMOUNT HAVING AMOUNT = (SELECT MAX(AMOUNT) FROM PENALTIES));

--    in which year there were the most penalties and how many were there

SELECT NR, YR
FROM (SELECT COUNT(PAYMENTNO) AS NR, EXTRACT(YEAR FROM PEN_DATE) AS YR
      FROM PENALTIES
      GROUP BY EXTRACT(YEAR FROM PEN_DATE)
      ORDER BY COUNT(PAYMENTNO) DESC)
WHERE ROWNUM <= 1;


--    PLAYERNO, TEAMNO, WON - LOST sorted by the the last.
SELECT PLAYERNO, TEAMNO, WON || ' - ' || LOST
FROM MATCHES
ORDER BY LOST;

-- 12-19 EmpDept query

--    output of all employees from department 30 sorted by their salary starting with the highest salary.
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO=30
ORDER BY SAL DESC;

--    output of all employees sorted by job and within the job by their salary
SELECT EMPNO, ENAME, JOB, SAL
FROM EMP
ORDER BY JOB, SAL;

--    output of all employees sorted by their year of employment in descending order and within the year by their name
SELECT EMPNO, ENAME, EXTRACT(YEAR FROM HIREDATE)
FROM EMP
ORDER BY EXTRACT(YEAR FROM HIREDATE), ENAME;

--    output of all salesmen in descending order regarding the ratio commission to salary
SELECT EMPNO, ENAME, COMM/SAL*100
FROM EMP
WHERE JOB='SALESMAN'
ORDER BY COMM/SAL*100 DESC;

--    output the average salary to each department number
SELECT DEPTNO, TRUNC(AVG(SAL))
FROM EMP
GROUP BY DEPTNO;

--    calculate the average annual salaries of those jobs that are performed by more than 2 employees
SELECT TRUNC(AVG(SAL)*12)
FROM EMP
GROUP BY JOB
HAVING COUNT(EMPNO)>2;

--    output all department numbers with at least 2 office workers
SELECT COUNT(*), DEPTNO
FROM EMP
WHERE JOB = 'CLERK'
GROUP BY DEPTNO
HAVING COUNT(*) >= 2;

--    find the average value for salary and commission of all employees from department 30
SELECT TRUNC(AVG(SAL)), TRUNC(AVG(COMM))
FROM EMP
WHERE DEPTNO=30;
