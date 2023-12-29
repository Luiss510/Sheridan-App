package com.example.javafxfinalproyect.beans;

public class Instructor {

    private String name;

    private String instructorID;

    private String department;

    public Instructor (String name, String instructorID, String department){
        this.name = name;
        this.instructorID = instructorID;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public String getDepartment() {
        return department;
    }


}
