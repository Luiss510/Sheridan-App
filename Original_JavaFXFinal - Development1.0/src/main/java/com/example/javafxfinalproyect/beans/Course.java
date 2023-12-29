/**
 * This class creates Course objects
 * each course has a name, a description, code, semester, and a list of enrolled students
 *
 * @author Luis Santos
 *
 */

package com.example.javafxfinalproyect.beans;

import java.util.ArrayList;

public class Course {

    private String name;

    private String description;

    private String code;

    private final String semester;

    private String imageURL;

    private ArrayList<Student> enrolledStudents = new ArrayList<>();


    /**
     * This method displays the details of the course
     * Course Constructor
     */
    public Course(String name, String description, String code, String semester, String image){
        this.name = name;
        this.description = description;
        this.code = code;
        this.semester = semester;
        imageURL = image;
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student s){
        if(s.getCurrentSemester().equals(semester) && !this.enrolledStudents.contains(s)){
            enrolledStudents.add(s);
            System.out.println("Student " + s.getName() + " was successfully enrolled in " + this.name);
        }else{
            System.out.println("Student cannot be enrolled.\n Course does not match for current semester or student is already enrolled");
        }
    }

    public void dropStudent(Student s){
        if(this.enrolledStudents.contains(s)){
            enrolledStudents.remove(s);
            System.out.println("Student " + s.getName() + " was successfully removed from " + this.name);
        }else{
            System.out.println("Student cannot be removed.\n Course does not match for current semester or student is not enrolled");
        }

    }
}
