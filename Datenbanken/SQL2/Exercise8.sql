--Exercise 8 on PARTS

--creating thePARTS table(parts.sql)
--display the whole hierarchy of those parts that make up P3 and P9
SELECT level, sub, super, price
FROM PARTS
START WITH SUPER = 'P3'
        OR SUPER = 'P9'
CONNECT BY PRIOR SUB = SUPER;

--at which hierarchy level is P12 used in P1
SELECT LEVEL
FROM PARTS
WHERE SUB = 'P12'
START WITH SUPER = 'P1'
CONNECT BY PRIOR SUB = SUPER;

--how many parts to P1 cost more than $20 to EMP-DEPT
SELECT SUB, PRICE
FROM PARTS
WHERE PRICE > 20
START WITH SUPER IS NULL
CONNECT BY PRIOR SUB = SUPER;

--output of all direct and indirect employees belonging to JONES (without JONES itself, with corresponding indentation per hierarchy)
SELECT LPAD(' ', lEVEL) || ENAME AS NAME, LEVEL
FROM EMP
START WITH MGR = 7566
CONNECT BY PRIOR EMPNO = MGR;

--output of all direct and indirect superiors of SMITH (including SMITH itself)
SELECT ENAME
FROM EMP
START WITH ENAME = 'SMITH'
CONNECT BY PRIOR MGR = EMPNO;

--output of the average salary for each hierarchy level
SELECT LVL AS "Level", ROUND(AVG(SAL)) AS "Average Salary"
FROM (SELECT SAL, LEVEL AS LVL
      FROM EMP
      START WITH MGR IS NULL
      CONNECT BY PRIOR EMPNO = MGR
     )
GROUP BY LVL
ORDER BY LVL;


