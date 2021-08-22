package com.sparta.csv.dto.ph.utils;

import com.sparta.csv.dto.ph.Filereader.EmployeeDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListConverter {

    public static List<EmployeeDto> ConvertHashMapToList(HashMap<Integer, EmployeeDto> employeeHashMap) {

        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Map.Entry<Integer, EmployeeDto> entry : employeeHashMap.entrySet()) {
            employeeDtos.add(entry.getValue());
        }
        return employeeDtos;
    }
}
