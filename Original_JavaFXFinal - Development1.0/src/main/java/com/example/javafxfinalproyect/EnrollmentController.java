/**
 * This class controls the FXML template that displays the courses the user can enroll in.
 *
 * @author Luis Santos
 *
 *     private ArrayList<Course> availableCourses; - > array List field to store the courses available for the user
 *
 *     private ArrayList<Course> enrolledCourses;  -> arrayList field to store the courses the user is currently enrolled in
 *
 *     private Student student; -> Student object field to store the the current student currently logged in
 *
 *     private DatabaseController db; -> Database field to store the current instance of the database
 *
 *     private Parent root; -> Parent field to control the current window being displayed
 *
 *     private Scene scene; -> Scene field to control the current window being displayed
 */
package com.example.javafxfinalproyect;


import com.example.javafxfinalproyect.beans.Course;
import com.example.javafxfinalproyect.beans.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EnrollmentController {

    @FXML
    private Label course1;
    @FXML
    private Label description1;
    @FXML
    private Button button1;
    @FXML
    private ImageView image1;

    @FXML
    private Label course2;
    @FXML
    private Label description2;
    @FXML
    private Button button2;
    @FXML
    private ImageView image2;

    @FXML
    private Label course3;
    @FXML
    private Label description3;
    @FXML
    private Button button3;
    @FXML
    private ImageView image3;

    @FXML
    private Label course4;
    @FXML
    private Label description4;
    @FXML
    private Button button4;
    @FXML
    private ImageView image4;

    @FXML
    private Label course5;
    @FXML
    private Label description5;
    @FXML
    private Button button5;
    @FXML
    private ImageView image5;

    private ArrayList<Course> availableCourses;
    private ArrayList<Course> enrolledCourses;

    private Student student;

    private DatabaseController db;

    private Parent root;
    private Scene scene;

    /**
     * This method displays the data of the student on the FXML (courses, program, etc)
     *
     * @param availableCourses1 Current available courses (data from the last window)
     * @param enrolledCourses1 courses the student is already enrolled (data from the last window)
     * @param student1 current student logged in
     * @param db1 Current instance of the database being used
     */
    public void DisplayCourses(ArrayList<Course> availableCourses1, ArrayList<Course> enrolledCourses1, Student student1, DatabaseController db1){
        //get input from the last window and assign them to the local variables (to enable access for any method in the class)
        db = db1;
        availableCourses = availableCourses1;
        enrolledCourses = enrolledCourses1;
        student = student1;

        //set data for the first course
        course1.setText(availableCourses.get(0).getName());
        description1.setText(availableCourses.get(0).getDescription());
        Image image = new Image(new File(availableCourses.get(0).getImageURL()).toURI().toString());
        image1.setImage(image);
        //check if the user is already enrolled in that course
        for(int i = 0; i < enrolledCourses.size(); i++){
            //if the current course is in "enrolledCourses" then don't let the user enroll again
            if(availableCourses.get(0).getName().equals(enrolledCourses.get(i).getName())){
                description1.setText("Already Enrolled!");
                button1.setVisible(false);
            }
        }

        //set data for the second course
        course2.setText(availableCourses.get(1).getName());
        description2.setText(availableCourses.get(1).getDescription());
        image = new Image(new File(availableCourses.get(1).getImageURL()).toURI().toString());
        image2.setImage(image);
        //check if the user is already enrolled in that course
        for(int i = 0; i < enrolledCourses.size(); i++){
            if(availableCourses.get(1).getName().equals(enrolledCourses.get(i).getName())){
                description2.setText("Already Enrolled!");
                button2.setVisible(false);
            }
        }


        //set data for the third course
        course3.setText(availableCourses.get(2).getName());
        description3.setText(availableCourses.get(2).getDescription());
        image = new Image(new File(availableCourses.get(2).getImageURL()).toURI().toString());
        image3.setImage(image);
        //check if the user is already enrolled in that course
        for(int i = 0; i < enrolledCourses.size(); i++){
            if(availableCourses.get(2).getName().equals(enrolledCourses.get(i).getName())){
                description3.setText("Already Enrolled!");
                button3.setVisible(false);
            }
        }

        //set data for the fourth course
        course4.setText(availableCourses.get(3).getName());
        description4.setText(availableCourses.get(3).getDescription());
        image = new Image(new File(availableCourses.get(3).getImageURL()).toURI().toString());
        image4.setImage(image);
        //check if the user is already enrolled in that course
        for(int i = 0; i < enrolledCourses.size(); i++){
            if(availableCourses.get(3).getName().equals(enrolledCourses.get(i).getName())){
                description4.setText("Already Enrolled!");
                button4.setVisible(false);
            }
        }

        //set data for the fifth course
        //use try/catch since some programs may not have 5 courses per semester (minimum 4)
        try{
            course5.setText(availableCourses.get(4).getName());
            description5.setText(availableCourses.get(4).getDescription());
            image = new Image(new File(availableCourses.get(4).getImageURL()).toURI().toString());
            image5.setImage(image);
            //check if the user is already enrolled in that course
            for(int i = 0; i < enrolledCourses.size(); i++){
                if(availableCourses.get(4).getName().equals(enrolledCourses.get(i).getName())){
                    description5.setText("Already Enrolled!");
                    button5.setVisible(false);
                }
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Error while getting courses");
        }



    }

    /**
     * This method displays the next window for course enrollment. The window appears as a popUP
     * @param event the event that triggered the method (button click)
     * @exception IOException in case a file is not found
     */
    public void displayCoursePopUp(ActionEvent event) throws IOException {
        //get loader for next window FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        Parent root = loader.load();

        //create instance of the controller to invoke methods and pass data
        CoursePopUpController coursePopUpController = loader.getController();

        //get the id of the button that was clicked
        Button button = (Button) event.getSource();
        String buttonID = button.getId();

        //depending on the ID of the button clicked, display a different course
        if(buttonID.equals("button1")){
            coursePopUpController.displayCourseDetails(availableCourses.get(0), student, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + availableCourses.get(0).getName());

        }
        if (buttonID.equals("button2")){
            coursePopUpController.displayCourseDetails(availableCourses.get(1), student, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + availableCourses.get(1).getName());

        }
        if(buttonID.equals("button3")){
            coursePopUpController.displayCourseDetails(availableCourses.get(2), student, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + availableCourses.get(2).getName());

        }
        if (buttonID.equals("button4")){
            coursePopUpController.displayCourseDetails(availableCourses.get(3), student, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + availableCourses.get(3).getName());

        }
        if (buttonID.equals("button5")){
            coursePopUpController.displayCourseDetails(availableCourses.get(4), student, course1, db);
            System.out.println("Button pressed was " + buttonID + ". Displaying course " + availableCourses.get(4).getName());
        }

        //make window pop
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();
    }


    /**
     * This method sends the user back to main courses Window
     * @param event the event that triggered the method (button click)
     * @exception IOException in case a file is not found
     */
    public void goBackToMainCourses(ActionEvent event) throws IOException {
        //get current window and close it
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();

        //load main courses again
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainCourses.fxml"));
        root = loader.load();

        //create an instance of MainCourses Window controller to pass data to it
        MainTabController mainTabController = loader.getController();
        mainTabController.DisplayCurrentInfo(student, db);

        //display it
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
