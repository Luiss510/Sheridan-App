/**
 * This class controls the database for the app
 * from the contents of this database relies the data being displayed in the app
 *
 * @author Luis Santos
 *
 *     public ArrayList<Program> programs = new ArrayList<>(); -> locally available ArraList for Programs database
 *
 */
package com.example.javafxfinalproyect;

import com.example.javafxfinalproyect.beans.*;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class DatabaseController {

    public ArrayList<Program> programs = new ArrayList<>();

    public ArrayList<Schedule> schedules = new ArrayList<>();

    /**
     * This method gets the Student data from the studentDatabase.txt
     *
     * @return returns an ArrayList of students with data obtained from studentDatabase.txt
     */
    public static ArrayList<Student> getStudentDatabase(){
        //Create arraylist to store csv file data
        ArrayList<Student> studentsDB = new ArrayList<>();

        //use bufferReader to read from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("studentDatabase.txt"))) {
            String line;
            //read until the end of the file
            while ((line = reader.readLine()) != null) {
                //separate values
                String[] fields = line.split("\t");

                if(fields.length == 5){
                    //store line in a temporary array
                    Student buffer = new Student(fields[0],fields[1],fields[2],fields[3], fields[4]);

                    //add row to the Student Arraylist
                    studentsDB.add(buffer);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentsDB;
    }

    /**
     * This "Main" method loads a sample database that is used for testing of the app
     * It creates Programs, Courses, and keeps track of the status and information of each student while the app is running
     *
     */
    public void main(){

        //create programs
        //Computer Science
        String descript = "sample description";
        Program cs = new Program("Computer Science", "PCSSN", descript, 8);
        programs.add(cs);

        //Art Fundamentals
        descript = "sample description";
        Program fundies = new Program("Art Fundamentals", "PARTF", descript, 2);
        programs.add(fundies);

        //Art Fundamentals
        descript = "sample description";
        Program music = new Program("Music Theatre", "PBAMT", descript, 8);
        programs.add(music);

        //create courses for each program
        //CS courses for semester 1
        descript = "Students acquire the foundation for future programming courses by being immersed in the science " +
                "of developing computer programs using an intuitive and productive hands-on approach. ";
        Course cs1 = new Course("Programming Principles", descript, "PROG10004", "1","images/prvwProgrammingPrinciples.jpg");

        descript = "Students explore the steps required to develop software, " +
                "the stages of the software development process, and the role of the computer scientist at each stage.";
        Course cs2 = new Course("Math for computing", descript, "MATH10025", "1", "images/prvwMathForComputing.jpg");

        descript = "Students learn the mathematical foundations of computer science. ";
        Course cs3 = new Course("Computer Science as a Field of Work and Study", descript, "INFO15514", "1", "images/prvwComputerScienceField.jpg");

        descript = "Students learn the architecture, the defining technologies and the standards " +
                "that comprise the edge and packet core of an internetwork.";
        Course cs4 = new Course("Network Foundations", descript, "TELE10025", "1", "images/prvwNetworkFoundations.jpg");

        descript = "Composition & Rhetoric is an advanced-level English course which focuses on the art of argument and persuasion.";
        Course cs5 = new Course("Composition and Rhetoric", descript, "ENGL17889", "1", "images/prvwComposition.jpg");

        programs.get(0).addCourses(cs1);
        programs.get(0).addCourses(cs2);
        programs.get(0).addCourses(cs3);
        programs.get(0).addCourses(cs4);
        programs.get(0).addCourses(cs5);

        //Art fundamentals Courses for semester1;
        descript = "Introduction to 2D Design is the first of a two part introduction to design " +
                "fundamentals which introduces the students to basic essentials of design.";
        Course fundies1 = new Course("Introduction to 2D design",descript,"DESN12719","1", "images/prvw2Ddesign.jpg");

        descript = "Introduction to 2D Design is the first of a two part introduction to design " +
                "fundamentals which introduces the students to basic essentials of design.";
        Course fundies2 = new Course("Introduction to 3D design",descript,"DESN15039","1", "images/prvw3Ddesign.jpg");

        descript = "In this classical figure drawing course, students draw from life models and explore " +
                "elements and principles of drawing and composition using traditional methods and materials.";
        Course fundies3 = new Course("Introduction to Drawing",descript,"ARTS10702","1","images/prvwDrawing.jpg");

        descript = "Students are introduced to instruction on how to represent and " +
                "convert three-dimensional objects using analytical drawing systems.";
        Course fundies4 = new Course("Introduction to Drawing Systems",descript,"DESN19667","1", "images/prvwDrawingSystems.jpg");

        descript = "Introduction to colour involves the application of the elements and principles " +
                "of colour theory to assigned projects using a variety of colour systems.";
        Course fundies5 = new Course("Introduction to Colour Theory",descript,"ARTS14636","1", "images/prvwColorTheory.jpg");

        descript = "Students develop transferable communication skills required for both academic and professional success, " +
                "including writing, reading, speaking, presenting, listening, visual and media literacy.";
        Course fundies6 = new Course("Essential Communication Skills",descript,"COMM19999","1", "");

        programs.get(1).addCourses(fundies1);
        programs.get(1).addCourses(fundies2);
        programs.get(1).addCourses(fundies3);
        programs.get(1).addCourses(fundies4);
        programs.get(1).addCourses(fundies5);
        programs.get(1).addCourses(fundies6);

        //Music Theatre Courses for semester 4
        descript = "Students examine the major theories and practices of Contemporary/Postmodern and Post-dramatic performance. ";
        Course music1 = new Course("Acting 4", descript,"THET24142","4","images/prvwActing4.jpg");

        descript = "Students investigate the breadth and expansive potential of musical theatre.";
        Course music2 = new Course("Vocal Music 4", descript,"THET25436","4","images/prvwVocalMusic.jpg");

        descript = "In interactive lectures, students explore character development and storytelling within dance.";
        Course music3 = new Course("Dance 4", descript,"THET23672","4","images/prvwDance4.jpg");

        descript = "Students further develop musicianship skills through music reading, writing, conducting and listening. ";
        Course music4 = new Course("Music Theatre 4", descript,"THET20587","4","images/prvwMusicTheatre4.jpg");

        programs.get(2).addCourses(music1);
        programs.get(2).addCourses(music2);
        programs.get(2).addCourses(music3);
        programs.get(2).addCourses(music4);


        //ENROLL SAMPLE STUDENTS IN THEIR PROGRAMS
        ArrayList<Student> students = getStudentDatabase();
        for (int i = 0; i < students.size(); i++){
            String studentProgram = students.get(i).getProgram();
            for (int y = 0; y < programs.size(); y++){
                if (studentProgram.equals(programs.get(y).getName())){
                    programs.get(y).addStudents(students.get(i));
                }
            }
        }

        //ADD SAMPLE INSTRUCTORS FOR COMPUTER SCIENCE COURSES
        Instructor csInstructor1 = new Instructor("Matt Willis", "982323456", "Computing");
        Instructor csInstructor2 = new Instructor("Brad Pitt", "985698741", "Computing");
        Instructor csInstructor3 = new Instructor("Juan Gonzales", "986547236", "Computing");
        Instructor csInstructor4 = new Instructor("Sameer Hadaad", "986745217", "Computing");
        Instructor csInstructor5 = new Instructor("Aubrey Gillis", "987456356", "Computing");
        Instructor csInstructor6 = new Instructor("Laureu Croissant", "987586398", "English");

        //ADD SCHEDULES FOR COURSE #1
        Schedule scheduleCS1_1 = new Schedule( cs1, csInstructor1);
        scheduleCS1_1.addClassTime(DayOfWeek.WEDNESDAY, LocalTime.of(16,0), LocalTime.of(18,0));
        scheduleCS1_1.addClassTime(DayOfWeek.FRIDAY, LocalTime.of(8,0), LocalTime.of(11,0));

        Schedule scheduleCS1_2 = new Schedule( cs1, csInstructor2);
        scheduleCS1_2.addClassTime(DayOfWeek.MONDAY, LocalTime.of(8,0), LocalTime.of(11,0));
        scheduleCS1_2.addClassTime(DayOfWeek.WEDNESDAY, LocalTime.of(19,0), LocalTime.of(21,0));


        //ADD SCHEDULES FOR COURSE #2
        Schedule scheduleCS2_1 = new Schedule(cs2, csInstructor3);
        scheduleCS2_1.addClassTime(DayOfWeek.TUESDAY, LocalTime.of(8,0), LocalTime.of(11,0));
        scheduleCS2_1.addClassTime(DayOfWeek.THURSDAY, LocalTime.of(8,0), LocalTime.of(11,0));

        //ADD SCHEDULES FOR COURSE #3
        Schedule scheduleCS3_1 = new Schedule(cs3, csInstructor4);
        scheduleCS3_1.addClassTime(DayOfWeek.WEDNESDAY, LocalTime.of(12,0), LocalTime.of(15,0));
        scheduleCS3_1.addClassTime(DayOfWeek.THURSDAY, LocalTime.of(8,0), LocalTime.of(11,0));

        //ADD SCHEDULES FOR COURSE #4
        Schedule scheduleCS4_1 = new Schedule(cs4, csInstructor5);
        scheduleCS4_1.addClassTime(DayOfWeek.MONDAY, LocalTime.of(18,0), LocalTime.of(21,0));
        scheduleCS4_1.addClassTime(DayOfWeek.TUESDAY, LocalTime.of(12,0), LocalTime.of(15,0));

        //ADD SCHEDULES FOR COURSE #5
        Schedule scheduleCS5_1 = new Schedule(cs5, csInstructor6);
        scheduleCS5_1.addClassTime(DayOfWeek.WEDNESDAY, LocalTime.of(18,0), LocalTime.of(21,0));
        scheduleCS5_1.addClassTime(DayOfWeek.FRIDAY, LocalTime.of(12,0), LocalTime.of(14,0));




        //ADD CS SCHEDULES TO ARRAY
        schedules.add(scheduleCS1_1);
        schedules.add(scheduleCS1_2);
        schedules.add(scheduleCS2_1);
        schedules.add(scheduleCS3_1);
        schedules.add(scheduleCS4_1);
        schedules.add(scheduleCS5_1);




        System.out.println("Database updated. Active\n\n\n");
    }
}
