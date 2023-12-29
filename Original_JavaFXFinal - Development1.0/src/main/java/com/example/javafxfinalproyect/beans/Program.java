/**
 * This class creates Program objects
 * each program has a name, a description, code, amount of semesters,
 * a list of enrolled students, and a list of courses
 *
 * @author Luis Santos
 *
 */

package com.example.javafxfinalproyect.beans;

import java.util.ArrayList;

public class Program {

    private String name;

    private String code;

    private String description;


    private int semesters;

    private ArrayList<Course> courses = new ArrayList<>();

    private ArrayList<Student> studentsEnrolled = new ArrayList<>();

    public Program(String name, String code, String description, int semesters){
        this.code = code;
        this.name = name;
        this.description = description;
        this.semesters = semesters;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemesters() {
        return semesters;
    }

    public String getName() {
        return name;
    }

    public void setSemesters(int semesters) {
        this.semesters = semesters;
    }

    public ArrayList<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course c){
        if(!courses.contains(c)){
            courses.add(c);
            System.out.println("Course " + c.getName() + " was succesfully added to " + name);
        }
        else {
            System.out.println("Course " +c.getName() + " could not be added to program " + name);
        }

    }

    public void addStudents(Student s){
        if(!studentsEnrolled.contains(s)){
            studentsEnrolled.add(s);
            System.out.println("Student " + s.getName() + " was successfully enrolled in " + this.name);
        }else{
            System.out.println("Student cannot be enrolled.\nstudent is already enrolled");
        }
    }
}
