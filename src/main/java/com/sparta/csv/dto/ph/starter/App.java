package com.sparta.csv.dto.ph.starter;


import com.sparta.csv.dto.ph.Filereader.CsvFileReader;
import com.sparta.csv.dto.ph.Filereader.EmployeeDto;
import com.sparta.csv.dto.ph.employeetreadcontroller.ConnectionManager;
import com.sparta.csv.dto.ph.employeetreadcontroller.EmployeeDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionManager.connectToDB();

        EmployeeDAO employeeDAO = new EmployeeDAO(connection);

        HashMap<Integer,EmployeeDto> employeeDToList = CsvFileReader.readFromFile("src/main/resources/EmployeeRecords.csv");

        System.out.println(employeeDToList.size() + " Size of unique employees.");

    }
}