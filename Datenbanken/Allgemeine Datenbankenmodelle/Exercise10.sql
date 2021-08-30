-- to the tennis club tables

--  insert a new record in the PLAYERS table (use your own data)
insert into PLAYERS
values (1, 'Kerbl', 'M', 1974, 'M', 1991, 'Yellow Brickrd', 99, '123456', 'München', '00000000', null);

--  change the value 'F' in the column SEX to 'W
update PLAYERS
set sex='W'
where sex = 'F';

--  increase all penalties above the average by 20%.
update PENALTIES
set amount=amount * 1.2
where amount > (select avg(amount)
                from penalties);


-- the player with the number 95 gets the address of the player with the number 6
update PLAYERS
set (street, houseno)=(select street, houseno from players where PLAYERNO = 6)
where playerno = 95;

-- deleting all penalties of player 44 from 1980
delete
from penalties
where playerno = 44
  and extract(year from pen_date) = 1980;

-- fix changes from 1.-5.
-- deletingallpenaltiesofplayerswhohaveplayedatleastonce in a team of the second division
delete
from PENALTIES
where PLAYERNO in (select playerno
                   from matches
                   where teamno in (select TEAMNO
                                    from TEAMS
                                    where division = 'second')
                   group by playerno)
;

-- deletingfrom7.undoing

-- to EMP-DEPT

-- delete all salaries that are lower than 80% of the average salary of the division, set to 80% of the average salary of the division
update EMP a
set sal=0.8 * (Select avg(sal)
               from emp b
               where a.deptno = b.deptno
)
where sal < 0.8 * (Select avg(sal)
                   from emp b
                   where a.deptno = b.deptno
)
;

-- delete all employees who have been with the company for more than 35 years
delete
from EMP
where (current_date - HIREDATE) / 365.25 > 35;

-- create a number sequence with the values 50, 60, 70, 80, ...
create sequence myseq
    minvalue 50
    maxvalue 100
    start with 50
    increment by 10;


-- insert a new record in the DEPT table with DEPTNO corresponding to the number sequence from 11., DNAME 'HTL' and LOC 'LEONDING'.
insert into DEPT
values (myseq.nextval, 'HTL', 'LEONDING');