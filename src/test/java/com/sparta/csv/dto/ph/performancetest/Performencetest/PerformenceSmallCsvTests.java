package com.sparta.csv.dto.ph.performancetest.Performencetest;

import com.sparta.csv.dto.ph.Filereader.CsvFileReader;
import com.sparta.csv.dto.ph.Filereader.EmployeeDto;
import com.sparta.csv.dto.ph.employeetreadcontroller.ConnectionManager;
import com.sparta.csv.dto.ph.employeetreadcontroller.EmployeeDAO;
import com.sparta.csv.dto.ph.employeetreadcontroller.ThreadContoller;
import com.sparta.csv.dto.ph.performancetest.testutils.TestTimer;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformenceSmallCsvTests {
    private static final int SMALL = 8;
    private static final int MEDIUM = 30;
    private static final int LARGE = 50;
    private static final int EmpCountsmall = 9943;
    private static final String file_path_small="src/main/resources/EmployeeRecords.csv";
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

        System.out.println("SmallFilesingleTreadTest " + resultSingle);
        System.out.println("smallFilemultiThreadSmallSizeTest Thread szie 8: "+resultSmall);
        System.out.println("smallFilemultiThreadMidiumSizeTest Thread szie 30: "+resultMedium);
        System.out.println("smallFilemultiThreadLargeSizeTest Thread szie 50: "+resultLarge);

    }
    @Test
    void SmallFilesingleTreadTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList = CsvFileReader.readFromFile(file_path_small);
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

        Assertions.assertEquals( EmpCountsmall, employeeDAO.getTableCount(tableName));
        resultSingle = "singlethread:" + timeTaken + "s.";
    }
    @Test
    void smallFilemultiThreadSmallSizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_small);
        threadContoller.multiTreadInsert(employeeDToList, SMALL);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals( EmpCountsmall, employeeDAO.getTableCount(tableName));
        resultSmall = "multiThreadTest (" + SMALL + "+1):" + timeTaken + "s.";
    }
    @Test
    void smallFilemultiThreadMidiumSizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_small);
        threadContoller.multiTreadInsert(employeeDToList, MEDIUM);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals( EmpCountsmall, employeeDAO.getTableCount(tableName));
        resultSmall = "multiThreadTest (" + MEDIUM + "+1):" + timeTaken + "s.";
    }
    @Test
    void smallFilemultiThreadLargeSizeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        HashMap<Integer, EmployeeDto> employeeDToList =
                CsvFileReader.readFromFile
                        (file_path_small);
        threadContoller.multiTreadInsert(employeeDToList, LARGE);
        float timeTaken = 0;
        while (employeeDAO.getTableCount(tableName) <= employeeDToList.size()) {
            if (employeeDAO.getTableCount(tableName) == employeeDToList.size()) {
                timeTaken = timer.end();
                break;
            }
        }
        Assertions.assertEquals( EmpCountsmall, employeeDAO.getTableCount(tableName));
        resultSmall = "multiThreadTest (" + LARGE + "+1):" + timeTaken + "s.";
    }

}
