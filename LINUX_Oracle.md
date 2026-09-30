https://share.google/aimode/0gPG00KGyLK1Ns1Bu

https://share.google/aimode/xCmuwpzp9l0Ljn58E

https://share.google/aimode/TjjcBWLpjhrmijwHf

https://share.google/aimode/qRlzGVvBN2J2irukT

Password: colyear


$ sqlplus sys as sysdba
SQL> STARTUP

CREATE USER B3980612345 IDENTIFIED BY B3980612345;

SQL> CREATE USER B2026132613074 IDENTIFIED BY B2026132613074;

```sql
-- 1. Create the user with the specified password
CREATE USER B3980612345 IDENTIFIED BY B3980612345;

-- 2. Grant basic privileges so the user can log in and create tables
GRANT CREATE SESSION TO B3980612345;
GRANT CREATE TABLE TO B3980612345;
GRANT CREATE SEQUENCE TO B3980612345;

-- 3. Grant quota so the user can actually store data in tables
ALTER USER B3980612345 QUOTA UNLIMITED ON USERS;

```

SQL> SHOW user;

SQL> SELECT sys_context('USERENV', 'DB_NAME') FROM dual;

CREATE TABLE ACCOUNT_TBL ( Account_Number VARCHAR2(10) PRIMARY KEY,  Customer_Name VARCHAR2(15),   Balance NUMBER(10,2));



## Trubleshoot oracle
$ lsnrctl start
$ sudo /etc/init.d/oracle-xe start



## Full Proof steps
https://share.google/aimode/nHuZxRxJgv41Mew3C





# Connect to Oracle database remotley
https://share.google/aimode/lJk63pOaU4Wh0g72Y

https://share.google/aimode/6aDGJUUL9KZo51yQv






# Eclipse installation
Linux: [Link](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2026-09/R/eclipse-jee-2026-09-R-linux-gtk-x86_64.tar.gz)

Folder : /home/pc/eclipse/jee-2026-09

/home/pc/eclipse/jee-2026-09