package com.sparta.csv.dto.ph.employeetreadcontroller;

import com.sparta.csv.dto.ph.Filereader.EmployeeDto;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ThreadContoller {
    Connection connection= ConnectionManager.connectToDB();
    public void multiTreadInsert(HashMap<Integer, EmployeeDto> employees,int numberOfThreads){
        int size=employees.size();
        int nuberofEntriesPerThread=size/numberOfThreads;
        int iterCount=0;

        HashMap<Integer,EmployeeDto> tempHashMap=new HashMap<>();

        for(Map.Entry<Integer,EmployeeDto>entry:employees.entrySet()){
            iterCount +=1;
            tempHashMap.put(entry.getKey(),entry.getValue());
            //check each segment
            if(iterCount%nuberofEntriesPerThread==0&&iterCount!=0){
               // System.out.println("Split"+iterCount);
                new Thread(new AddTask(tempHashMap)).start();
                tempHashMap = new HashMap<>();
            }
            else if (iterCount == size) {
                 //System.out.println("last entry! " + iterCount);

                new Thread(new AddTask(tempHashMap)).start();
                //System.out.println(tempHashMap.size());
                tempHashMap.clear();
            }

        }


    }
}
