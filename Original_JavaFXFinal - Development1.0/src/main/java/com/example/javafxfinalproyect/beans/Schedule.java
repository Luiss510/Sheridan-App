package com.example.javafxfinalproyect.beans;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<ClassTime> classes = new ArrayList<>();


    private Course course;

    private Instructor instructor;

    public Schedule( Course course, Instructor instructor){
        this.course = course;
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }


    public Instructor getInstructor() {
        return instructor;
    }



    public void addClassTime(DayOfWeek day, LocalTime startTime, LocalTime endTime){
        ClassTime buffer = new ClassTime(day, startTime, endTime);
        classes.add(buffer);
    }


    public String toString(){
        return instructor.getName() + "\n"
                + classes.get(0).toString() + "\n"
                + classes.get(1).toString();
    }


}
