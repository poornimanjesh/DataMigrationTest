# Sparta.DataMigration

Reads a CSV file containing employee data of several thousand rows
Populate a MySQL database running on the local server
The employee data is validated against multiple validation standards in order to then populate the database
Each entry of the CSV file stores the following information:
Employee ID
Title
First Name
Middle Initial
Last Name
Gender
Email Address
Date of Birth
Date of Joining the Company
Salary
All invalid or duplicate data is stored in separate CSV files that can be found inside resources directory
Once the database is populated with CSV entries, the user can then manipulate the database from the command line and perform operations such as
Selecting an employee by ID
Deleting an employee by ID
Getting a current number of employees in the database
The process of writing a CSV file to the program and populating the database is measured for performance speed
The performance of the manipulations performed on the database is also measured and displayed upon completion

Tools Used
JDK 11
MySQL
IntelliJ
MySQL Workbench 8.0 CE
JUnit 5
Git
Topics Covered within Functionality
OOP Principles
SOLID Principles
Unit Testing
Performance Testing
Defined Packages
MVC Design Pattern
MySQL Java Integration
HashMaps
Iterations
Parallel Threading

Example Test Results


 
  
