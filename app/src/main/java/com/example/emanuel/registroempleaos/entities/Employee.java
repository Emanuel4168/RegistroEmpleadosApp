package com.example.emanuel.registroempleaos.entities;

public class Employee {
    private int id;
    private int edad;
    private String name;

    public Employee(int edad, String name, int id) {
        this.edad = edad;
        this.name = name;
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
