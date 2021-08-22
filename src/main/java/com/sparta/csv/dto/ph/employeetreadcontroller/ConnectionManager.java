package com.sparta.csv.dto.ph.employeetreadcontroller;

import com.sparta.csv.dto.ph.utils.Propertiesloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;
    public static Connection connectToDB(){

        String Url = Propertiesloader.getProperties().getProperty("url");        //"jdbc:mysql://localhost:3306/user_db";
        String Username = Propertiesloader.getProperties().getProperty("userName");
        String password = Propertiesloader.getProperties().getProperty("password");

        try {
            connection = DriverManager.getConnection(Url, Username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
