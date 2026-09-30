# Oracle Database Environment Verification Guide
## Preferably: Oracle Database 11g Express Edition (XE)


This guide provides a systematic workflow to locate your Oracle installations on Ubuntu, initialize the native Oracle 11g XE database engine, audit instance health, and manage custom application schemas.

---

### Step 1: Locate the Oracle Installation
Run these reconnaissance commands to identify active containers, native configuration files, and dedicated system users on your machine.

```bash
# Check for any containerized Oracle instances running via Docker
docker ps -a | grep -i oracle

# Locate native database entry paths on the filesystem
cat /etc/oratab 2>/dev/null || cat /etc/oraInst.loc 2>/dev/null

# Confirm the existence of the local Oracle Linux user account
grep "oracle" /etc/passwd
```

---

### Step 2: Initialize Database & Run Diagnostics
Switch to the native environment layer to initialize the engine, check the runtime execution state, and list all configured database schemas.

```bash
# 1. (optional) Switch to the native oracle system administrator account
sudo su - oracle
        If prompted enter your PC root password

# 2. (optional) Source the environment profile to load binary paths (Note the space after the dot)
. /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh

# 3. Access the command-line interface as the root administrator
sqlplus sys as sysdba

# 4. Boot up the database instance (Skip if already running)
SQL> startup

# 5. Check the operational instance status and engine health
SQL> SELECT instance_name, status, database_status FROM v$instance;

# 6. Audit all system users, account locks, and structural creation dates
SQL> SELECT username, account_status, created FROM dba_users ORDER BY username;
```

---

### Step 3: Manage Application Schema & User Switching
Isolate your development space by spinning up a dedicated application database schema, then navigate back and forth between administrative contexts.

#### A. Create the New Application Database User
While logged in under your administrative `SYS` context, execute the following configuration script:

```sql
# Create the user and set a unique password
SQL> CREATE USER spring_boot_app IDENTIFIED BY YourSecurePassword123;

# Grant baseline session, connection, and data manipulation capabilities
SQL> GRANT CONNECT, RESOURCE, CREATE VIEW TO spring_boot_app;

# Provide an unlimited space allocation block inside the standard storage tablespace
SQL> ALTER USER spring_boot_app QUOTA UNLIMITED ON USERS;
```

#### B. Switch FROM Administrative User TO Application User
Transition out of the root system workspace into your new database runtime environment to test access:

```sql
# Authenticate directly into the application space
SQL> CONNECT spring_boot_app/YourSecurePassword123;

# Verify your current active user workspace session
SQL> SHOW USER;
# Output: USER is "SPRING_BOOT_APP"
```

#### C. Switch BACK TO Administrative User
Return to the master control layer to perform system-level management actions:

```sql
# Reconnect back into the master SYS control account
SQL> CONNECT sys as sysdba;

# Confirm you have successfully reverted back to root space
SQL> SHOW USER;
# Output: USER is "SYS"
```

## Dbeaver GUI to Manage oracle: Configurations  
Click on new connection icon and apply configurations as
```config
• Connection Type: Basic
• Host: localhost
• Port: 1521
• Databse: XE
• Databse/SID: SID
• Username: User-database_name (example: college)
• Password: User-database_Password (example: college)
```
Press button Test Connection

## Trubleshoot Dbeaver GUI to Manage oracle 
```bash
sudo su - oracle
. /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh

lsnrctl start

$ sqlplus sys as sysdba
SQL> startup
SQL> ALTER SYSTEM REGISTER;

```
After this you should see  
```text
Connection Test:
Connected
Server: Oracle Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

Driver: Oracle JDBC driver 23.2.0.0.0
```

## Safley Close for the day
```bash
# in SQL > terminal
SQL> shutdown immediate;
SQL> exit
```

```bash
# in oracle user terminal
lsnrctl stop
# stops the network listner
exit
# Log out of the oracle user (returns you back to your regular pc user)
```

## Tomorrow (Starting Up Again)
- Launch DBeaver from your applications menu
```bash
sudo su - oracle
. /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh
lsnrctl start 
sqlplus sys as sysdba
SQL> startup
```

