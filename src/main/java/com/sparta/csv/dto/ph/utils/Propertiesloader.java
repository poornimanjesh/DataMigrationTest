package com.sparta.csv.dto.ph.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Propertiesloader {
    public static Properties getProperties(){
        Properties properties=new Properties();

        try {
            properties.load(new FileReader("src/database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
return properties;
    }
}
