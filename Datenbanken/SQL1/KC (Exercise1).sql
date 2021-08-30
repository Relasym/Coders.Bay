

-- 1    Die Personalabteilung wünscht eine Abfrage, um für jeden Angestellten den Nachnamen, die Job-Kennung (JOB_ID), das Einstellungsdatum und die Angestelltennummer anzuzeigen, wobei die Angestelltennummer als erster Wert angezeigt werden soll. Geben Sie den Alias STARTDATE für die Spalte HIRE_DATE an.
SELECT EMPLOYEE_ID, LAST_NAME, JOB_ID, HIRE_DATE AS STARTDATE
FROM EMPLOYEES;


-- 2    Die Personalabteilung benötigt eine Abfrage, um alle eindeutigen Job-Kennungen (JOB_ID) aus der Tabelle EMPLOYEES anzuzeigen. Duplikate sind zu vermeiden.
SELECT DISTINCT JOB_ID
FROM EMPLOYEES;


-- 3    Die Personalabteilung wünscht aussagekräftigere Spaltenüberschriften für die auf Angestellte bezogenen Berichte. Verwenden Sie die Anweisung aus Ausgabe 3.1 und geben Sie den Spalten die Überschriften Emp #, Employee, Job und Hire Date. Führen Sie die Abfrage erneut aus.
SELECT EMPLOYEE_ID AS "Emp #", LAST_NAME as Employee, JOB_ID as Job, HIRE_DATE AS "Hire Date"
FROM EMPLOYEES;


-- 4    Zu Budgetzwecken benötigt die Personalabteilung einen Bericht, der den Nachnamen und das Gehalt für Angestellte anzeigt, die mehr als $ 12.000 verdienen. Führen Sie die Abfrage aus.
SELECT LAST_NAME, SALARY
FROM EMPLOYEES
WHERE SALARY>12000;


-- 5    Erstellen Sie einen Bericht, um den Nachnamen und die Abteilungsnummer für den Angestellten mit der Angestelltennummer 176 anzuzeigen.
SELECT LAST_NAME, DEPARTMENT_ID
FROM EMPLOYEES
WHERE EMPLOYEE_ID=176;


-- 6    Erstellen Sie einen Bericht, um den Nachnamen, die Job-Kennung (JOB_ID) und das Einstellungsdatum aller Angestellten anzuzeigen. Sortieren Sie die Abfrage in aufsteigender Reihenfolge nach Einstellungsdatum.
SELECT LAST_NAME, JOB_ID, HIRE_DATE
FROM EMPLOYEES
ORDER BY HIRE_DATE;


-- 7    Zeigen Sie Nachnamen und Abteilungsnummern aller Angestellten der Abteilung 20 an, alphabetisch nach Nachnamen in aufsteigender Reihenfolge sortiert.
SELECT LAST_NAME, DEPARTMENT_ID
FROM EMPLOYEES
WHERE DEPARTMENT_ID=20
ORDER BY LAST_NAME;


-- 8    Erstellen Sie eine Anfrage, die Nachnamen, Gehälter und Provisionen aller Angestellten anzeigt, deren Provision 20 % beträgt. Geben Sie den Spalten die Überschriften Employee, Monthly Salary und Commission.
SELECT LAST_NAME as Employee, SALARY as "Monthly Salary", COMMISSION_PCT*100 as Commision
FROM EMPLOYEES
WHERE COMMISSION_PCT=0.2;

SELECT EMPLOYEES.DEPARTMENT_ID, trunc(AVG(EMPLOYEES.SALARY))AS "AVG SALARY", D.DEPARTMENT_NAME
FROM EMPLOYEES
INNER JOIN DEPARTMENTS D on D.DEPARTMENT_ID = EMPLOYEES.DEPARTMENT_ID
GROUP BY EMPLOYEES.DEPARTMENT_ID,DEPARTMENT_NAME
ORDER BY "AVG SALARY"