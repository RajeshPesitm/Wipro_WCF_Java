###### JDK folder: C:\\Program Files\\Java\\jdk-21.0.12.1\\

###### 

###### Oracle download: https://www.oracle.com/in/database/technologies/xe-downloads.html

###### 

###### Installation Guide: https://docs.oracle.com/en/database/oracle/oracle-database/21/xeinw/index.html

###### 

###### 

###### Oracle: C:\\app\\Admin\\product\\21c\\

###### Oracle home: C:\\app\\Admin\\product\\21c\\dbhomeXE

###### 

###### Oracle 21C Express Edition

###### 

###### Oracle password Pesitm2020

###### 

###### \# command

###### &#x20;sqlplus system/Pesitm2020

###### 

###### 

###### \# How i downloaded Eclipse and VS code

###### Eclipse and VS code downloaded zip file and Application can be launched directly







\# Quizz Platforms

wayground quiziz

Kahoot


-------------------------------------------------------------------------------------------

Day - 7

\-------------------------------------------------------------------------------------------

Connecting to oracle

SQL> CREATE USER wcf IDENTIFIED  BY wcf123  
// Trouble Shoot
SQL> SHOW CON_NAME;


SELECT name, open_mode
FROM v$pdbs;




\## Further commands
SQL> ALTER SESSION SET CONTAINER = XEPDB1;



SQL> SHOW CON_NAME;



SQL> CREATE USER wcf IDENTIFIED BY wcf123;



SQL> grant connect,resource,dba to wcf;

SQL> exit

 sqlplus system/Pesitm2020@localhost:1521/XEPDB1


SHOW USER;

SHOW CON\_NAME;

### You Should get

```text
You should get:



USER is "SYSTEM"



CON\_NAME

\------------------------------

XEPDB1

```



SELECT username, account\_status

FROM dba\_users

WHERE username = 'WCF';

# instructions
https://chatgpt.com/s/t\_6aa8f7c282b08191927f134d77b4da1d



https://chatgpt.com/s/t\_6aa8f8b2d6008191ae86b93cc68491f1







