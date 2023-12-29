/**
 * This class creates a Student object
 * each student has an ID, a password, name, program, and semester
 *
 * @author Luis Santos
 *
 */

package com.example.javafxfinalproyect.beans;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Student {

    private String studentID;

    private String password;

    private String name;

    private String program;

    private String currentSemester;

    private ArrayList<Schedule> schedule = new ArrayList<>();



    public Student(String id, String studentName,String p, String studentProgram, String semester ){
        studentID = id;
        password = p;
        name = studentName;
        program = studentProgram;
        currentSemester = semester;
    }
    public String getCurrentSemester() {
        return currentSemester;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setCurrentSemester( String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }


    public void addDatabase(){
        String fileName = "studentDatabase.txt";
        try {
            // Create a new PrintWriter with the file name
            FileWriter writer = new FileWriter("studentDatabase.txt", true);

            // Write data to file
            writer.write(studentID + "\t" + name + "\t" + password + "\t" + program + "\t" + currentSemester + "\n");

            // Close the writer to save changes
            writer.close();

            System.out.println("Data written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    public void addSchedule(Schedule schedule){
        this.schedule.add(schedule);
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }
}
