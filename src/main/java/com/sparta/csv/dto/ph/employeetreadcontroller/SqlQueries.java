package com.sparta.csv.dto.ph.employeetreadcontroller;

public interface SqlQueries {
//    String SELECT_FROM_DB="select*from user_db.users";
    String INSERT_IN_Employee_DB ="INSERT INTO `emp`.`employee` (`EmpID`," +
            " `NamePrefix`," +
            " `FirstName`, " +
            "`MiddleInitial`," +
            " `LastName`, " +
            "`Gender`, " +
            "`EMail`, " +
            "`DateOfBirth`, " +
            "`Dateofjoin`," +
            " `Salary`) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";
}
