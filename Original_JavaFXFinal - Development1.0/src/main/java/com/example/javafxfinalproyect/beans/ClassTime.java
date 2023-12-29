package com.example.javafxfinalproyect.beans;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ClassTime {

    private DayOfWeek day;

    private LocalTime startTime;

    private LocalTime endTime;

    public ClassTime(DayOfWeek day, LocalTime startTime, LocalTime endTime){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public String toString(){
        return day + " " + startTime + "-" + endTime;
    }


}
