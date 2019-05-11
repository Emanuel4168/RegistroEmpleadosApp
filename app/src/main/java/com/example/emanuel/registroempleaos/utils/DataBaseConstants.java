package com.example.emanuel.registroempleaos.utils;

public class DataBaseConstants {

    public static String EMPLOYEE_TABLE = "EMPLOYEE";
    public static String ID_FIELD = "id";
    public static String NAME_FIELD = "name";
    public static String AGE_FIELD = "age";

    public static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE "+EMPLOYEE_TABLE+"("+ID_FIELD+" INTEGER, " +NAME_FIELD+" TEXT, " +AGE_FIELD +" INTEGER)";
    public static final String DROP_TABLE_EMPLOYEE = "DROP TABLE IF EXISTS "+EMPLOYEE_TABLE;
}
