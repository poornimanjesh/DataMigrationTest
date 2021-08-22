package com.sparta.csv.dto.ph.performancetest.Performencetest;

import com.sparta.csv.dto.ph.Filereader.CsvFileReader;
import com.sparta.csv.dto.ph.Filereader.EmployeeDto;
import com.sparta.csv.dto.ph.employeetreadcontroller.ConnectionManager;
import com.sparta.csv.dto.ph.employeetreadcontroller.EmployeeDAO;
import com.sparta.csv.dto.ph.employeetreadcontroller.ThreadContoller;
import com.sparta.csv.dto.ph.performancetest.testutils.TestTimer;
import org.junit.jupiter.api.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PreformenceLargeCsvTests {
    private static final int SMALL = 15;
    private static final int MEDIUM = 58;
    private static final int LARGE = 70;
    private static final int EmpCountLargre = 65499;

    private static final String file_path_large="src/main/resources/EmployeeRecordsLarge.csv";
    private static final String tableName="emp.employee";
    private static EmployeeDAO employeeDAO;
    private static CsvFileReader csvFileReader;
    private static ThreadContoller threadContoller;
    static ConnectionManager connectionManager;
    private static String resultSingle = "";
    private static String resultSmall = "";
    private static String resultMedium = "";
    private static String resultLarge = "";

    @BeforeAll
    static void init() {
        csvFileReader = new CsvFileReader();
        employeeDAO = new EmployeeDAO(ConnectionManager.connectToDB());
        threadContoller = new ThreadContoller();

    }

    @BeforeEach
    void deleteInsertions() {
        try {
            employeeDAO.deleteInsertions(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void printResults() throws SQLException {
        employeeDAO.deleteInsertions(tableName);
        System.out.println("largefilesingleTreadTest" + resultSingle);
        System.out.println(" largefilemultiThreadSmallsizeTest Thread size 15: "+resultSmall);
        System.out.println("largefilemultiThreadMidiumSizeTestThread size 60: "+resultMedium);
        System.out.println("largeFilemultiThreadLargeSizeTest Thread size 70: "+resultLarge);

    }

    @Test
    void largefilesingleTreadTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList = CsvFileReader.readFromFile(file_path_large);
        //System.out.println(employeeDToList.size());
        List<EmployeeDto> emps = new ArrayList<>();
        for (Map.Entry<Integer, EmployeeDto> entry : employeeDToList.entrySet()) {
            emps.add(entry.getValue());
        }

        employeeDAO.insertMultipulRecords(emps);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }

        Assertions.assertEquals(EmpCountLargre, employeeDAO.getTableCount("emp.employee"));
        resultSingle = " largefilesingleTreadTest:" + timeTaken + "s.";
    }

    @Test
    void largefilemultiThreadSmallsizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_large);
        threadContoller.multiTreadInsert(employeeDToList, SMALL);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals(EmpCountLargre, employeeDAO.getTableCount(tableName));
        resultSmall = "multiThreadTest (" + SMALL + "+1):" + timeTaken + "s.";
    }

    @Test
    void largefilemultiThreadMidiumSizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_large);
        threadContoller.multiTreadInsert(employeeDToList, MEDIUM);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals(EmpCountLargre, employeeDAO.getTableCount(tableName));
        resultMedium = "multiThreadTest(" + MEDIUM + "+1):" + timeTaken + "s.";
    }

    @Test
    void largeFilemultiThreadLargeSizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_large);
        threadContoller.multiTreadInsert(employeeDToList, LARGE);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals(EmpCountLargre, employeeDAO.getTableCount(tableName));
        resultLarge = "multiThreadTest (" + LARGE + "+1):" + timeTaken + "s.";
    }

}
