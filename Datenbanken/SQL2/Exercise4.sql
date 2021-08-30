--Exercise 4
--1-6 Tennis query

--  output TEAMNO of the teams in which the player with the number 27 is not captain
SELECT TEAMNO
FROM TEAMS
WHERE PLAYERNO != 27;

--  output of PLAYERNO, NAME and INITIALS of the players who have won at least one match
SELECT PLAYERS.PLAYERNO, NAME, INITIALS
FROM PLAYERS,
     MATCHES
WHERE WON > 0
  AND PLAYERS.PLAYERNO = MATCHES.PLAYERNO
GROUP BY PLAYERS.PLAYERNO, NAME, INITIALS;

--  output of playerNo and name of the players who have received at least one penalty
SELECT PLAYERS.PLAYERNO, NAME
FROM PLAYERS,
     PENALTIES
WHERE PLAYERS.PLAYERNO = PENALTIES.PLAYERNO
GROUP BY PLAYERS.PLAYERNO, NAME;

--  output of playerNo and name of the players, who have received at least one penalty over 50.
SELECT PLAYERS.PLAYERNO, NAME
FROM PLAYERS,
     PENALTIES
WHERE PLAYERS.PLAYERNO = PENALTIES.PLAYERNO
  AND AMOUNT > 50
GROUP BY PLAYERS.PLAYERNO, NAME;

--  output of PlayerNo and name of players born in the same year as R. Parmenter
SELECT PLAYERNO, NAME
FROM PLAYERS
WHERE YEAR_OF_BIRTH =
      (SELECT YEAR_OF_BIRTH
       FROM PLAYERS
       WHERE NAME = 'Parmenter'
         AND INITIALS = 'R');

--  output of playerNo and name of the oldest player from Stratford
SELECT PLAYERNO, NAME
FROM PLAYERS
WHERE TOWN = 'Stratford'
  AND YEAR_OF_BIRTH =
      (SELECT MIN(YEAR_OF_BIRTH)
       FROM PLAYERS);

--7-12 EmpDept query

--  search all departments, which have no employees

SELECT DEPTNO, DNAME
FROM DEPT
WHERE DEPTNO NOT IN
      (SELECT DISTINCT DEPTNO
       FROM EMP);

--  search all employees who have the same job as JONES
SELECT EMPNO, ENAME
FROM EMP
WHERE JOB = (SELECT JOB FROM EMP WHERE ENAME = 'JONES');


--  show all employees who make more than any employee from department 30
SELECT EMPNO, ENAME
FROM EMP
WHERE SAL>(SELECT MAX(SAL) FROM EMP WHERE DEPTNO=30);

--  show all employees who earn more than any employee from department 30
SELECT EMPNO, ENAME
FROM EMP
WHERE NVL(SAL,0) + NVL(COMM,0)>(SELECT MAX(NVL(SAL,0) + NVL(COMM,0)) FROM EMP WHERE DEPTNO=30);

--  display all employees from department 10 whose job is not held by any employee from department 30
SELECT EMPNO, ENAME
FROM EMP
WHERE JOB NOT IN (SELECT DISTINCT JOB FROM EMP WHERE DEPTNO=30);

--  search for the employee data (EMPNO, ENAME, JOB, SAL) of the employee with the highest salary.
SELECT EMPNO, ENAME, JOB, SAL
FROM EMP
WHERE SAL=(SELECT MAX(SAL) FROM EMP);
