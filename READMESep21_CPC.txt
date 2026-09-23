https://chatgpt.com/s/t_6ab0c98022fc819186c1b9a85384ee7b
https://chatgpt.com/s/t_6ab0d1cae6588191a28dd48bf1d2a2f6
https://chatgpt.com/s/t_6ab0d1aa1af081918ec4ec36de0e6e88
https://chatgpt.com/s/t_6ab0c98022fc819186c1b9a85384ee7b

PS C:\WINDOWS\system32> sqlplus system@localhost:1521/XE

SQL> SELECT con_id, name, open_mode FROM v$pdbs;

SQL> ALTER SESSION SET CONTAINER = XEPDB1;

SQL> SELECT SYS_CONTEXT('USERENV', 'CON_NAME') AS CONTAINER FROM DUAL;




SQL> CREATE USER B2026132613074 IDENTIFIED BY B2026132613074;


SQL> SELECT username FROM dba_users WHERE oracle_maintained = 'N' ORDER BY created;


## Batchnumber and PBLA APP ID: don't share to any one
202613
2613074
B2026132613074



Step 2:
https://chatgpt.com/s/t_6ab0dfdd525881918fd774a869a927c3
SQL> GRANT CONNECT, RESOURCE TO B2026132613074;
SQL> COMMIT;


```SQL
SELECT u.username,
       o.object_name AS table_name,
       o.created
FROM dba_users u
JOIN dba_objects o
  ON o.owner = u.username
WHERE u.oracle_maintained = 'N'
  AND o.object_type = 'TABLE'
ORDER BY u.created, o.created, u.username, o.object_name;
```


Step 3:
https://chatgpt.com/s/t_6ab0e90f1d448191a63636ff57d65695


PS C:\WINDOWS\system32> sqlplus B2026132613074/B2026132613074@localhost:1521/XEPDB1

SQL> SELECT USER FROM DUAL;

SQL> CREATE TABLE CANDIDATE_TBL (
        ID      VARCHAR2(6)  PRIMARY KEY,
        NAME    VARCHAR2(15),
        M1      NUMBER(3),
        M2      NUMBER(3),
        M3      NUMBER(3),
        RESULT  VARCHAR2(15),
        GRADE   VARCHAR2(15)
    );





Step 4:
```SQL
CREATE SEQUENCE CANDID_SEQ
    MINVALUE 5000
    MAXVALUE 7000
    START WITH 5000
    INCREMENT BY 1;
```
