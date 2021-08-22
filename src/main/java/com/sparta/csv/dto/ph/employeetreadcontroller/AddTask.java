package com.sparta.csv.dto.ph.employeetreadcontroller;

import com.sparta.csv.dto.ph.Filereader.EmployeeDto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTask implements Runnable {
    public final HashMap<Integer,EmployeeDto> splitHashMap;
    List<EmployeeDto> empList = new ArrayList<>();
    EmployeeDAO employeeDAO;

    public AddTask(HashMap<Integer, EmployeeDto> splitHashMap) {
        this.splitHashMap = new HashMap<>( splitHashMap);
    }

    @Override
    public void run() {
        Connection connection= ConnectionManager.connectToDB();
        employeeDAO=new EmployeeDAO(connection);

        for (Map.Entry<Integer,EmployeeDto>entry:splitHashMap.entrySet()) {
            empList.add(entry.getValue());
        }

        employeeDAO.insertMultipulRecords(empList);

    }
}
