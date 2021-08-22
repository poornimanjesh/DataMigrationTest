package com.sparta.csv.dto.ph.Filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvFileReader {
    public static HashMap<Integer,EmployeeDto> readFromFile(String fileName){
        HashMap<Integer,EmployeeDto> employeeDtoList = new HashMap<>();
        List<EmployeeDto> dupEmployeeList = new ArrayList();

        try {
            FileReader fileReader=new FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            int counter =0;
            for(String line=bufferedReader.readLine();line != null;line=bufferedReader.readLine()){

                if(counter>0) {
                    String[] attributes = line.split(",");

                    if(!employeeDtoList.containsKey(Integer.parseInt(attributes[0]) )) {
                        EmployeeDto employeeDto = createEmployee(attributes);
                        employeeDtoList.put(employeeDto.getEmpId(), employeeDto);
                    }
                }

                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employeeDtoList;
    }
    public static EmployeeDto createEmployee(String[] attributes) {
        Integer empId = Integer.parseInt( attributes[0]);
        String namePrefix= attributes[1];
        String firstName = attributes[2];
        String middleInitial=attributes[3];
        String lastName = attributes[4];
        String gender=attributes[5];
        String email=attributes[6];
        String DoB=attributes[7];
        String DoJ=attributes[8];
        Integer salary=Integer.parseInt( attributes[9]);

        return new EmployeeDto(
                empId,
                namePrefix,
                firstName,
                middleInitial,
                lastName,
                gender,
                email,
                DoB,
                DoJ,
                salary);

    }

}
