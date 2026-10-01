I will feed an online Java assignment. Because i cant download the document as it is in web page. generate copy block to copy that.


Here is the clean copyable version:

```
Project Assessment

GENERAL INSTRUCTIONS: Please carefully read the below instructions

The objective of this assessment is to check your ability to complete a project as per the provided “Project Design”.

You are expected to –

1. Write the source code for the classes, methods and packages EXACTLY as mentioned in the “Project Design” section.

2. Ensure that the names of the packages, classes, methods and variables EXACTLY MATCH with the names specified in the “Project Design” section.

3. Understand the project requirements and ACCORDINGLY WRITE the code and logic in the classes and methods so as to meet all given requirements.
```

Here is the clean copyable version:

```
Banking Application

Project Objective:

Create a console based Java application that would allow the customer of a bank to perform day to day bank transactions. The following are the tasks that need to be performed by the Customer.

1. View balance.

2. Transfer amount.

Overview:

View balance: If the account number is given the balance should be returned

Transfer Amount: This function is used to transfer money from one account to another account.

For the operation to be successful, the following conditions are to be met.

1. Both the account numbers should be valid

2. The account number from where the money is transferred should have enough money for performing the transfer operation

If all these conditions are met, the given amount has to be debited from the payer and credited to the beneficiary (account_tbl) and an entry has to be made in the transfer_tbl
```

```
A. Database Design:

1. Create a new user in database [ To be done in the backend by using sql commands ]

a) Note: Do NOT use the default scott/tiger account of oracle for this project. You will have to create a new user in the below mentioned format.

b) Username/password: B<batchnumber><employeeid>

For example, if your batch number is 39806 and Employee number is 12345, then the oracle user should be B3980612345 and the password should be B3980612345

c) For JDBC connection, only use XE as service name and 1521 as port number

2. Steps for creating a new user

a) Open command prompt

b) Sqlplus / as sysdba

c) Create user <username> identified by <password>; [ For example to create a user named“test” with password “test”: create user test identified by test; ]

d) Grant connect,resource to <username>; [ E.g: grant connect,resource to test;]

e) Commit;

f) Exit;
```


```

```
3. Create Table

To be done using SQL commands, after logging in as the new user that was created in the above step.

------------------------------------------------------------
Table Name: ACCOUNT_TBL
------------------------------------------------------------

Values for this table will be hardcoded directly.

| Column        | Datatype      | Description          |
|---------------|---------------|----------------------|
| Account_Number| Varchar2(10)  | Primary Key          |
| Customer_Name | Varchar2(15)  | Account holder name  |
| Balance       | Number(10,2)  | Account Balance      |

Insert some records into the ACCOUNT_TBL.

Sample Records:

| ACCOUNT_NUMBER | CUSTOMER_NAME | BALANCE |
|----------------|---------------|---------|
| 1234567890     | Reddy         | 80000   |
| 1234567891     | Mahesh        | 0       |
| 1234567892     | Dhanu         | 100     |
| 1234567893     | Sam           | 500     |

------------------------------------------------------------
Table Name: TRANSFER_TBL
------------------------------------------------------------

| Column                       | Datatype      | Description                                      |
|------------------------------|---------------|--------------------------------------------------|
| Transaction_ID               | Number(4)     | Primary Key                                      |
| Account_Number               | Varchar2(10)  | Foreign Key, references Account_Number field    |
|                              |               | of ACCOUNT_TBL                                   |
| Beneficiary_account_number   | Varchar2(10)  | Foreign Key, references Account_Number field    |
|                              |               | of ACCOUNT_TBL                                   |
| Transaction_Date             | Date          | Date of transaction                              |
| Transaction_Amount           | Number(10,2)  | Amount to be transferred                         |
```

```
4. Create Sequence

------------------------------------------------------------
Sequence Name: transactionId_seq
------------------------------------------------------------


| Sequence Name   | Minimum Value | Maximum Value | Incremental Value | Start Value |
|-----------------|---------------|---------------|-------------------|-------------|
| transactionId_seq| 1000          | 9999          | 1                 | 1000        |


B. System Design
------------------------------------------------------------
The application should contain the following packages:
------------------------------------------------------------


| Package Name             | Usage |
|--------------------------|-------|
| com.wipro.bank.service   | This package will contain the class that displays the console menu and takes user input. It will also contain methods that perform validation on the given input and invoke the respective DAO operations. |
| com.wipro.bank.bean      | This package will contain the entity class named `TransferBean`. |
| com.wipro.bank.dao       | This package will contain the class that performs database-related JDBC operations. |
| com.wipro.bank.util      | This package will contain the class used to establish the database connection and the class that handles the user-defined exception. |
```

```
Package: com.wipro.bank.util

| Class                    | Method and Variables                          | Description |
|--------------------------|-----------------------------------------------|-------------|
| DBUtil                   | DB connection class                           |             |
|                          | `public static Connection getDBConnection()` | Establish a connection to the database and return the `java.sql.Connection` reference. |
| InsufficientFundsException | User-defined exception class                |             |
|                          | `public String toString()`                    | Returns the String `"INSUFFICIENT FUNDS"`. The details about when it has to be thrown are given in the appropriate methods. |
```

```
Package: com.wipro.bank.bean

Class: TransferBean

| Method / Variable | Description |
|-------------------|-------------|
| `private int transactionID` | Transaction ID |
| `private String fromAccountNumber` | Account number from where money is going to be transferred. <br><br>**Maps to:** `Account_Number` field of `TRANSFER_TBL` |
| `private String toAccountNumber` | Account number to where money is going to be transferred. <br><br>**Maps to:** `Beneficiary_account_number` field of `TRANSFER_TBL` |
| `private Date dateOfTransaction` | Date on which the transaction is taking place — current date (`java.util.Date`). |
| `private float amount` | Amount to be transferred. |
| Setters & Getters | Create getter and setter methods for all the attributes mentioned in the class. |
```

```
Package: com.wipro.bank.dao

Class: BankDAO

| Method / Variables | Description |
|--------------------|-------------|
| `public int generateSequenceNumber()` | Generates a 4-digit auto-generated number using the `transactionId_seq` sequence. |
| `public boolean validateAccount(String accountNumber)` | Checks `ACCOUNT_TBL` and returns `true` if the account number is valid; otherwise, returns `false`. |
| `public float findBalance(String accountNumber)` | Checks `ACCOUNT_TBL` and returns the balance if the `accountNumber` is valid; otherwise, returns `-1`. |
| `public boolean transferMoney(TransferBean transferBean)` | - Inserts the `TransferBean` values into `TRANSFER_TBL`.<br>- The `transactionID` is the value obtained from `generateSequenceNumber()`.<br>- The transaction date is today's date.<br>- On successful insertion, returns `true`; otherwise, returns `false`. |
| `public boolean updateBalance(String accountNumber, float newBalance)` | - Updates `ACCOUNT_TBL` with the `newBalance` for the given `accountNumber`.<br>- Returns `true` for successful updation and `false` otherwise. |
```

```
Package: com.wipro.bank.service

Class: BankMain

| Method / Variables | Description |
|--------------------|-------------|
| `public static void main(String[] args)` | The code needed to test the program goes here. A sample code is shown at the end of the document. |
| `public String checkBalance(String accountNumber)` | **Steps to perform:**<br><br>1. Invoke the appropriate `BankDAO` methods and perform the following:<br>   - Validate the `accountNumber`.<br>   - If valid, find the balance for the given `accountNumber`.<br>   - Return the message in the given format.<br><br>For example, if the balance returned by the `findBalance` method is `10000`, the return value should be:<br>`BALANCE IS:10000.0`<br><br>2. If the `AccountNumber` is invalid, return:<br>`ACCOUNT NUMBER INVALID` |
| `public String transfer(TransferBean transferBean)` | **Steps to perform:**<br><br>1. If `transferBean` is `null`, the function should return `"INVALID"`.<br><br>2. Validate both account numbers in the `TransferBean`. If any of the account numbers are invalid, the function should return:<br>`INVALID ACCOUNT`<br><br>3. If both account numbers are valid, check whether the `fromAccountNumber` has sufficient funds to transfer.<br><br>4. The function will throw `InsufficientFundsException` if the payer does not have sufficient money. The exception should be caught in the same method itself. If the exception is caught, the function should return:<br>`INSUFFICIENT FUNDS`<br><br>**Note:** Do not use `System.exit(0)` while handling the exception.<br><br>5. If the payer has enough money, update `ACCOUNT_TBL` for both account numbers to perform the transfer operation:<br>   - Reduce the given amount from `fromAccountNumber`.<br>   - Add the given amount to `toAccountNumber`.<br>   - Invoke the `transferMoney` function of the `BankDAO` class to include the transaction details in `TRANSFER_TBL`.<br><br>6. If Step 5 is successful, the method should return:<br>`SUCCESS` |
```

```
Main Method:

You can write code in the main method and test all the above test cases.

A sample code of the main method to test the first test case is shown below for your reference.

public static void main(String[] args) {

    // View Balance
    System.out.println(bankMain.checkBalance("1234567890"));

    // Transfer Money
    TransferBean transferBean = new TransferBean();

    transferBean.setFromAccountNumber("1234567890");
    transferBean.setAmount(500);
    transferBean.setToAccountNumber("1234567891");
    transferBean.setDateOfTransaction(new java.util.Date());

    System.out.println(bankMain.transfer(transferBean));
}
```

```
Test Cases

Below is the actual set of test cases that the CPC test engine will run in the background. Please ensure that the conditions mentioned in these test cases are handled by your class design.

1. Test for Sequence Number Creation

2. Test for Balance Checking with Valid Account Number

3. Test for Balance Checking with Invalid Account Number

4. Test for Successful Transfer of Funds

5. Test for Transfer with Low Funds

6. Test for Transfer with Zero Balance

7. Test for Transfer with Invalid Payer Account Number

8. Test for Transfer with Invalid Beneficiary Account Number
```

