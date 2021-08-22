package com.sparta.csv.dto.ph.Filereader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeDto {
    private Integer EmpId;
    private String NamePrefix;
    private String FirstName;
    private String MiddleInitial;
    private String LastName;
    private String Gender;
    private String EMail;
    private String DateofBirth;
    private String DateofJoining;
    private Integer Salary;

    public Integer getEmpId() {
        return EmpId;
    }

    public String getNamePrefix() {
        return NamePrefix;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleInitial() {
        return MiddleInitial;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public String getEMail() {
        return EMail;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public String getDateofJoining() {
        return DateofJoining;
    }

    public Integer getSalary() {
        return Salary;
    }

    public EmployeeDto(Integer empId,
                       String namePrefix,
                       String firstName,
                       String middleInitial,
                       String lastName,
                       String gender,
                       String EMail,
                       String dateofBirth,
                       String dateofJoining,
                       Integer salary) {
        EmpId = empId;
        NamePrefix = namePrefix;
        FirstName = firstName;
        MiddleInitial = middleInitial;
        LastName = lastName;
        Gender = gender;
        this.EMail = EMail;
        DateofBirth = dateofBirth;
        DateofJoining = dateofJoining;
        Salary = salary;
    }

}
