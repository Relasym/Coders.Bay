--Exercise 7
--1-4 Tennis query

--output of players' names who played for both team 1 and team 2.
SELECT NAME
FROM PLAYERS
WHERE PLAYERNO IN (SELECT PLAYERNO FROM MATCHES WHERE TEAMNO = 1)
  AND PLAYERNO IN (SELECT PLAYERNO FROM MATCHES WHERE TEAMNO = 2);

--output the NAME and INITIALS of the players who did not receive a penalty in 1980
SELECT NAME, INITIALS
FROM PLAYERS
WHERE PLAYERNO NOT IN (SELECT PLAYERNO
                       FROM PENALTIES
                       WHERE EXTRACT(YEAR FROM PEN_DATE) = 1980);


SELECT PLAYERS.PLAYERNO, NAME, INITIALS
FROM PLAYERS, PENALTIES
WHERE PLAYERS.PLAYERNO=PENALTIES.PLAYERNO(+)
AND (PEN_DATE IS NULL OR EXTRACT(YEAR FROM PEN_DATE)!=1980)
GROUP BY NAME, INITIALS, PLAYERS.PLAYERNO;

SELECT *
FROM PLAYERS, PENALTIES
WHERE PLAYERS.PLAYERNO=PENALTIES.PLAYERNO(+);


--output of players who received at least one penalty over $80
SELECT *
FROM PLAYERS
WHERE PLAYERNO IN (SELECT PLAYERNO
                   FROM PENALTIES
                   WHERE AMOUNT > 80);

--output of players who had any penalty over $80.
SELECT *
FROM PLAYERS
WHERE PLAYERNO IN (SELECT PLAYERNO
                   FROM PENALTIES
                   GROUP BY PLAYERNO
                   HAVING SUM(AMOUNT) > 80);

--5-8 EmpDept query

--find all employees whose salary is higher than the average salary of their department
SELECT *
FROM EMP,
     (SELECT AVG(SAL) AS AVGSAL, DEPTNO FROM EMP GROUP BY DEPTNO) AVGTBL
WHERE SAL > AVGTBL.AVGSAL;

--identify all departments that have at least one employee
SELECT DEPTNO
FROM DEPT
WHERE DEPTNO IN (SELECT EMP.DEPTNO FROM EMP);

--output of all departments that have at least one employee earning over $1000
SELECT DEPTNO
FROM DEPT
WHERE DEPTNO IN (SELECT EMP.DEPTNO FROM EMP WHERE SAL > 1000);

--output of all departments in which each employee earns at least 1000,-.
SELECT DEPTNO
FROM DEPT
WHERE DEPTNO NOT IN (SELECT EMP.DEPTNO FROM EMP WHERE SAL < 1000)
