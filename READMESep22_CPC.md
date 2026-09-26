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



Additional Knowledge: #Tips to run SQL commands in powershell  
step1: makesure SQL> prompt  
```SQL
ed
```
step 2: paste query in a buffer file and save and close (no need to terminate with ; or separators , between lines, try experiment i am also not sure)  
step 3: give this below command in >SQL prompt  
```SQL
/
```

commands to try
```SQL
SELECT table_name
FROM user_tables
ORDER BY table_name
```

```SQL
SELECT sequence_name
FROM user_sequences
ORDER BY sequence_name
```

















































------------------------------------------------------------------
package com.wipro.candidate.bean;

public class CandidateBean {
	private String id;
	private String name;
	private  int m1;
	private  int m2;
	private  int m3;
	private String result;
	private String grade;
	public CandidateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getM1() {
		return m1;
	}
	public void setM1(int m1) {
		this.m1 = m1;
	}
	public int getM2() {
		return m2;
	}
	public void setM2(int m2) {
		this.m2 = m2;
	}
	public int getM3() {
		return m3;
	}
	public void setM3(int m3) {
		this.m3 = m3;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
-------------------------------------------------------------------
package com.wipro.candidate.dao;


import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;


public class CandidateDAO {
	public String addCandidate(CandidateBean studentBean)
	{
			String status="";
			//write code here
			return status;
	}
	public ArrayList<CandidateBean> getByResult(String criteria)
	{
		ArrayList<CandidateBean> list=new ArrayList<CandidateBean>();
		//write code here
		return list;
	}
	public String generateCandidateId (String name)
	{
		String id="";
		//write code here
		return id;
	}
}
---------------------------------------------
package com.wipro.candidate.service;

import java.util.ArrayList;


import com.wipro.candidate.bean.CandidateBean;


public class CandidateMain {

	/**
	 * @param args
	 */
	public String addCandidate(CandidateBean studBean)
	{
		String result="";
	    //write code here
	    return result;
		
	}
	public ArrayList<CandidateBean> displayAll(String criteria)
	{
		return null;
		
		//write code here
		
	}
	public static void main(String[] args) {
		//write code here
		System.out.println("Hello world!!");
	}

}
-----------------------------------------------------
package com.wipro.candidate.util;

import java.sql.Connection;


public class DBUtil {
public static Connection getDBConn()
{
	Connection con=null;
	//write code here
	return con;
}
}
---------------------------------------------------
package com.wipro.candidate.util;

public class WrongDataException extends Exception{

	@Override
	public String toString() {
			//write code here
		return "";
	}

}
 






 Package: com.wipro.candidate.bean



Class
CandidateBean

 
Description
Bean class

 
Method and Variables

private String id

Student Id

 

private String name

Student name

 

private  int m1

Mark in First Subject

 

private  int m2

Mark in Second Subject

 

private  int m3

Mark in Third Subject

 

private String result

Result

 

private String grade

Grade

 

setters & getters

Should create the getter and setter methods for all the attributes mentioned in the class





Package: com.wipro.candidate.dao

Class

Method and Variables

Description

Class
CandidateDAO

 
Description
DAO class

 Method and Variables

public String addCandidate(CandidateBean CandidateBean)

·         This method should take the values from the CandidateBean object and insert it into the database.

·         If the insertion is successful, then a String “SUCCESS” should be returned, else a String “FAIL” should be returned.

·         If any JDBC exception such as SQLException occur, this function should return “FAIL”

 
Method and Variables
public ArrayList<CandidateBean> getByResult(String criteria)

·         This method should use the JDBC select statement to retrieve the records based on the criteria given.

·         If the criteria String contains “PASS” then the getByResult(String criteria) function should return an ArrayList of all Candidates who have passed

·         If the criteria String contains “FAIL” then the getByResult(String criteria) function should return an  ArrayList of all Candidates who have failed

·         If the criteria String contains “ALL” then the getByResult(String criteria) function should return an ArrayList of all the Candidates

·         In any of the criteria’s “PASS/FAIL/ALL” if there are no matching records then the function should return null

·         In case of any JDBCExceptions in the database then a null value needs to be returned

 
Method and Variables
public String generateCandidateId (String name)

·         This method should contain the necessary code to create a new Candidate id.

·         CandidateID is a combination of first 2 letters of name in uppercase followed by 4 digit number that will be generated by the oracle sequence CANDID_SEQ.

·         For eg, the Candidate id for a Candidate Jacob could be JA2194

·         The function should return the generated Candidate id.

 

 

Package: com.wipro.candidate.service

Class

Method and Variables

Description

Class
CandidateMain

 
Description
Main class

 
Method and Variables
public static void main(String[] args)

The code that is needed to test your program goes here. 
Sample Code:
Main Method:

You can write code in the main method and test all the above test cases. A sample code of the main function to test the first test case is shown below for your reference.

public static void main(String[] args) {

CandidateMain candidateMain = new CandidateMain();

String result = candidateMain.addCandidate(null);

System.out.println(result);

}

 
Method and Variables
public String addCandidate(CandidateBean candBean)

·         This method should add a CandidateBean Object to the database.

·         The following are the conditions under which a user defined exception WrongDataException (found in com.wipro.candidate.util package) should be thrown.

Ø  candBean is null

Ø  candBean’s name is empty String

Ø  candBean’s name contains less than 2 characters

Ø   candBean’s mark1,mark2,mark3 contains marks which are  not within 0 to 100 range.

·         This exception should be handled within the addCandidate(CandidateBean candBean) function itself.

·         If this exception is caught, then the function is expected to return a String “Data incorrect”.

NOTE: Do NOT use System.exit(0) while handling the exception.

·         Compute Candidate ID

Ø  If the candBean object is valid, this function should call the generateCandidateId (String name) function of the CandidateDAO  class to obtain the candidate id. The candBean’s name should be passed as parameter to the generateCandidateId (String name) function

·         The candBean’s id should be initialized using the Candidate Id that is received in the previous step

·         Compute result and grade

Ø  The result and grade are computed using the following logic

Ø  M1=mark1 of candBean

Ø  M2=mark2 of candBean

Ø  M3=mark3 of candBean

Total Marks

Result

Grade

(M1+M2+M3)>= 240

PASS

Distinction

(M1+M2+M3)>=  180 and (M1+M2+M3)<240

PASS

First Class

(M1+M2+M3)>=  150 and (M1+M2+M3)<180

PASS

Second Class

(M1+M2+M3)>= 105 and (M1+M2+M3) <150

PASS

Third Class

(M1+M2+M3)  <105

FAIL

No Grade

·         Initialise the candBean’s result and grade with the computed values

·         Invoke addCandidate(CandidateBean CandidateBean) of the CandidateDAO class to insert the candBean into the database.

·         On successful storage of the Candidate Details, to the table,  the function should return the CandidateID and result of the particular CandidateBean 

·         [ E.g if the Candidate ID generated is   SA1001, and the result is PASS then the success message should be SA1001:PASS

·         If by any reason, the record is not stored, then the function should  return the String Error

 

public ArrayList<CandidateBean> displayAll(String criteria)

·         This method should return the collection of the Candidates from the Candidate table who are matching the given criteria

·         The criteria string can have values such as “PASS/FAIL/ALL”.

·         If the criteria contains either “PASS/FAIL/ALL” then invoke getByResult(String criteria) of CandidateDAO class and receive the collection

·         If the criteria String contains any other values then the WrongDataException need to be thrown, and the function should return a null value.

·         NOTE: Do NOT use System.exit(0) while handling the exception.

 

 AI Help
 https://share.google/aimode/rTwroSSVRqM6J3dZ8

 






















 Trouble Shoot:
 1. Add ojdbc.jar 11 version to project reference libraries
 2. grant your user account storage privileges in Oracle.

 How to Fix:  
 Open your terminal, connect via SQL*Plus as the administrative system user (sysdba), and grant B1234 an unlimited storage allocation quota with these exact steps:  
 Connect as SYSDBA:  
 ```SQL
 sqlplus sys/sys_password@localhost:1521/XEPDB1 as sysdba
 ```
(Note: replace sys_password with the master password you chose when installing Oracle XE).  
Execute the Quota Allocation Alter Statement:  
Run the following query to remove the table extension limits:  
```SQL
ALTER USER B1234 QUOTA UNLIMITED ON USERS;
```
Verify and Rerun:  
Type exit to close SQL*Plus, return to your project workspace, and run your CandidateMain class again.

AI help
https://share.google/aimode/KNkGjtwYXSekujzeE














# Generate Test Suit Using Junit


AI Help
https://share.google/aimode/AzRfn8WAPjiXiSUBC