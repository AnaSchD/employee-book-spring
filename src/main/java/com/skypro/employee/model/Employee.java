package com.skypro.employee.model;

import org.apache.commons.lang3.StringUtils;

public class Employee {

    private static int counter;
    private final int ID;
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final int DEPARTMENT;
    private final int SALARY;

    public Employee(String FIRST_NAME, String LAST_NAME, int DEPARTMENT, int SALARY) {

        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.DEPARTMENT = DEPARTMENT;
        this.SALARY = SALARY;
        this.ID = counter++;
    }

    public int getID() {
        return ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public int getDEPARTMENT() {
        return DEPARTMENT;
    }

    public int getSALARY() {
        return SALARY;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", FIRST_NAME='" + FIRST_NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", DEPARTMENT=" + DEPARTMENT +
                ", SALARY=" + SALARY +
                '}';
    }
}
