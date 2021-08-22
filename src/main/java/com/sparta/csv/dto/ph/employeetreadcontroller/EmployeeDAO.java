package com.sparta.csv.dto.ph.employeetreadcontroller;

import com.sparta.csv.dto.ph.Filereader.EmployeeDto;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeDAO {
    private Connection connection;
    public Statement statement;

    public EmployeeDAO(Connection connection){
        this.connection=connection;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRecord(int EmpID,
                             String NamePrefix,
                             String FirstName,
                             String MiddleInitial,
                             String LastName,
                             String Gender,
                             String EMail,
                             String DateOfBirth,
                             String Datofjoin,
                             int Salary) {
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_IN_Employee_DB);

            preparedStatement.setInt(1, EmpID);
            preparedStatement.setString(2,NamePrefix );
            preparedStatement.setString(3, FirstName);
            preparedStatement.setString(4, MiddleInitial);
            preparedStatement.setString(5, LastName);
            preparedStatement.setString(6, Gender);
            preparedStatement.setString(7, EMail);
            preparedStatement.setString(8, DateOfBirth);
            preparedStatement.setString(9, Datofjoin);
            preparedStatement.setInt(10, Salary);

            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //creating Multipul Records to send database. using this method in addtask
    public void insertMultipulRecords(List<EmployeeDto> employeeDtoList){
        // PreparedStatement preparedStatement=connection.prepareStatement(SqlQueries.INSERT_IN_Employee_DB);
        for(EmployeeDto e:employeeDtoList){
            createRecord(e.getEmpId(),e.getNamePrefix(),
                    e.getFirstName(),e.getMiddleInitial(),
                    e.getLastName(),e.getGender(),e.getEMail(),
                    e.getDateofBirth(),e.getDateofJoining(),e.getSalary()
            );
        }
    }

    //duplicate employee data
    public  List<EmployeeDto> onlyDuplicate(List<EmployeeDto> emloyees) {
        List<EmployeeDto> dupEmps = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        for (EmployeeDto e:emloyees) {
            if(!set1.add(e.getEmpId()))
            {
                dupEmps.add(e);
            }

        }
        return dupEmps;

    }

    //clean employee data
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public static List<EmployeeDto> getUniqueEmployees(List<EmployeeDto> employeeDtos)
    {
        List<EmployeeDto> uniqueEmployees = new ArrayList();
        uniqueEmployees =  employeeDtos.stream()
                .filter(distinctByKey(p->p.getEmpId())).collect(Collectors.toList());

        return uniqueEmployees;
    }
    public void deleteInsertions(String tableName) throws SQLException {
        PreparedStatement preparedStatement =
                null;
        try {
            String signString  = ">" ;

            preparedStatement = ConnectionManager.connectToDB().prepareStatement
                    ("DELETE FROM " + tableName + " where EmpId > ?");
            preparedStatement.setInt(1,0);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeConnection();
        }


    }
    public int getTableCount(String tableName) throws SQLException {
        PreparedStatement preparedStatement =
                null;
        try {
            Statement statement = ConnectionManager.connectToDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName + ";");
            resultSet.next();
            return resultSet.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement.executeUpdate();
        return 0;
    }

}



