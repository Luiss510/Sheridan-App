/**
 * This class controls the FXML template that displays the courses the user is currently enrolled in.
 *
 * @author Luis Santos
 *
 *     private Parent root; -> fields to manipulate which window is being displayed at the moment
 *     private Stage stage;
 *     private Scene scene;

 *
 *     private ArrayList<Course> enrolledCourses = new ArrayList<>(); -> field to store on which courses the user is enrolled in
 *
 *     private ArrayList<Course> availableCourses = new ArrayList<>(); -> field to store which courses the student has available according to his program.
 *
 *     private Student s; -> field to store the current student that is logged in
 *
 *     private DatabaseController db; -> field to store the instance of the database that is being used right now;
 *
 */
package com.example.javafxfinalproyect;

import com.example.javafxfinalproyect.beans.Course;
import com.example.javafxfinalproyect.beans.Program;
import com.example.javafxfinalproyect.beans.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainTabController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label programLabel;

    @FXML
    private Label semesterLabel;

    @FXML
    private Label course1;

    @FXML
    private Label status1;


    @FXML
    private Button details1;

    @FXML
    private Label course2;

    @FXML
    private Label status2;

    @FXML
    private Button details2;


    @FXML
    private Label course3;

    @FXML
    private Label status3;

    @FXML
    private Button details3;

    @FXML
    private Label course4;

    @FXML
    private Label status4;

    @FXML
    private Button details4;

    @FXML
    private Label course5;


    @FXML
    private Label status5;

    @FXML
    private Button details5;

    @FXML
    private Label course6;

    @FXML
    private Label status6;

    @FXML
    private Button details6;

    @FXML
    private Button logOut;

    private ArrayList<Course> enrolledCourses = new ArrayList<>();

    private ArrayList<Course> availableCourses = new ArrayList<>();

    private Student s;

    private DatabaseController db;


    /**
     * This method displays the data of the student on the FXML (courses, program, etc)
     *
     * @param student The current student logged in
     * @param db1 Current instance of the database being used
     */
    public void DisplayCurrentInfo(Student student, DatabaseController db1){
        //set student that is currently logged in
        s = student;

        //Set Window Top Labels
        welcomeLabel.setText("Welcome! " + s.getName());
        nameLabel.setText("Student ID: " + s.getStudentID());
        programLabel.setText("Program: " + s.getProgram());
        semesterLabel.setText("Current Semester: " + s.getCurrentSemester());

        //Set courses details
        //assign current database being used
        db = db1;
        //get access to all programs
        ArrayList<Program> programs = db.programs;
        boolean coursesFound = false;

        //for each program in database check if current student program matches any
        for (int i = 0; i < programs.size(); i++){
            //if current student program matches the program in database
            if(s.getProgram().equals(programs.get(i).getName())){
                //get its available courses
                availableCourses = programs.get(i).getCourses();
                coursesFound = true;
            }
        }

        //if courses where found
        if(coursesFound) {
            //get courses the student is in

            //create array list to get the courses he is in
            enrolledCourses = new ArrayList<>();

            //go through each of the courses
            for (int i = 0; i < availableCourses.size(); i++) {
                //for each course get the list of students
                ArrayList<Student> studentsInCourse = availableCourses.get(i).getEnrolledStudents();

                //go through the list of students and check if the current student is there
                for (int y = 0; y < studentsInCourse.size(); y++) {
                    if (studentsInCourse.get(y).getStudentID().equals(s.getStudentID())) {
                        //if student found, add to enrolled courses
                        enrolledCourses.add(availableCourses.get(i));
                    }
                }
            }

            //display enrolled courses on FXML
            try{
                course1.setText(enrolledCourses.get(0).getName());
                status1.setText("Status: Enrolled");
                details1.setDisable(false);

            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #1 on main page");

            }

            try{
                course2.setText(enrolledCourses.get(1).getName());
                status2.setText("Status: Enrolled");
                details2.setDisable(false);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #2 on main page");

            }

            try{
                course3.setText(enrolledCourses.get(2).getName());
                status3.setText("Status: Enrolled");
                details3.setDisable(false);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #3 on main page");

            }

            try{
                course4.setText(enrolledCourses.get(3).getName());
                status4.setText("Status: Enrolled");
                details4.setDisable(false);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #4 on main page");

            }

            try{
                course5.setText(enrolledCourses.get(4).getName());
                status5.setText("Status: Enrolled");
                details5.setDisable(false);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #5 on main page");

            }

            try{
                course6.setText(enrolledCourses.get(5).getName());
                status6.setText("Status: Enrolled");
                details6.setDisable(false);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("SYSTEM - Could not load course #6 on main page");

            }

        }
    }

    /**
     * This method laads the window for course enrollment.
     *
     * @param e The event that triggers the method (button click)
     * @throws IOException if file not found
     */
    public void loadEnrollmentWindow(ActionEvent e) throws IOException {

        //get loader for the next window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Enrollment.fxml"));
        root = loader.load();

        //create an instance of the controller for being able to pass data between scenes
        EnrollmentController enrollmentController = loader.getController();
        enrollmentController.DisplayCourses(availableCourses,enrolledCourses,s,db);

        //get current stage and change it for new scene
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method logs out the user and returns to the main Log In page.
     * Activated when Log out button is pressed
     *
     * @param event The event that triggers the method (button click)
     */
    public void logOut(ActionEvent event){

        //display an alert for the user to confirm if he wants to log out or not
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Click OK to confirm, or Cancel to stay logged in.");
        Optional<ButtonType> result = alert.showAndWait();

        //depending on the answer, go back to the Log In window
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 730, 550);
                Stage stage = (Stage) logOut.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayDetails(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsPopUp.fxml"));
        Parent root = loader.load();
        //DETERMINE WHICH COURSE IS BEING SELECTED

        //get button id
        Button button = (Button) event.getSource();
        String buttonID = button.getId();
        DetailsPopUpController detailsPopUpController = loader.getController();

        //depending on the button clicked, display the details window.
        if(buttonID.equals("details1")){
            //set up course for next window
            detailsPopUpController.displayCourseDetails(enrolledCourses.get(0), s, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + enrolledCourses.get(0).getName());
        }

        if(buttonID.equals("details2")){
            //set up course for next window
            detailsPopUpController.displayCourseDetails(enrolledCourses.get(1), s, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + enrolledCourses.get(1).getName());
        }

        if(buttonID.equals("details3")){
            //set up course for next window
            detailsPopUpController.displayCourseDetails(enrolledCourses.get(2), s, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + enrolledCourses.get(2).getName());
        }

        if(buttonID.equals("details4")){
            //set up course for next window
            detailsPopUpController.displayCourseDetails(enrolledCourses.get(3), s, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + enrolledCourses.get(3).getName());
        }

        if(buttonID.equals("details5")){
            //set up course for next window
            detailsPopUpController.displayCourseDetails(enrolledCourses.get(4), s, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + enrolledCourses.get(4).getName());
        }

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();
    }

}
