/**
 * This class controls the FXML template for the user to log in
 * it basically lets the user enter its student ID and password.
 * if the user does not have an account in the database (studentDatabase.txt) it will display error
 * the user then can request an account
 *
 * @author Luis Santos
 *
 *     private TextField studentID; -> to get access to the FXML student TextField
 *
 *
 *     private TextField password; -> to get access to the FXML password text field
 *
 *
 *     private Label error1; -> to get access to the  FXML error1 Label
 *
 *     protected static boolean logInSuccess = false; -> public variable that lets the whole class know if the log in was
 *     succesful.
 *
 *     private Parent root; -> Variables to handle the window being displayed at the moment
 *     private Stage stage
 *     private Scene scene
 *
 */

package com.example.javafxfinalproyect;

import com.example.javafxfinalproyect.beans.Student;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;

public class LogInController {


    @FXML
    private TextField studentID;

    @FXML
    private TextField password;

    @FXML
    private Label error1;
    @FXML
    private Label mainLabel;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Rectangle square1;
    @FXML
    private Rectangle square2;
    @FXML
    private Rectangle square3;
    @FXML
    private ImageView sheridanS;

    protected static boolean logInSuccess = false;

    private Parent root;
    private Stage stage;
    private Scene scene;


    /**
     * This method processes the studentID and password to check if they are valid inputs
     * if they are, it allows access to the user.
     *
     * @param event The event that triggers the method (button click)
     * @throws IOException if file not found
     */
    public void checkLogIn(ActionEvent event) throws IOException {
        //declare variables
        String student = studentID.getText();
        String key = password.getText();


        //check if Student ID is numerical and has correct length
        if(checkNumerical(student) && student.length() == 9){
           System.out.println("Student ID is numerical and has correct lenght");

           //check if id and password are valid and exist in database
            //get Student database from Database controller class
            ArrayList<Student> studentDB = DatabaseController.getStudentDatabase();

            //go through each student in database
            for ( int i = 0; i < studentDB.size(); i++){
                //if the currentStudent matches an ID and Password in the database
                if(studentDB.get(i).getStudentID().equals(student) && studentDB.get(i).getPassword().equals(key)){

                    //if it exists, print LOG IN SUCCESSFUL
                    System.out.println("log in successful");
                    logInSuccess = true;

                    //DISPLAY LOADING SCREEN
                    //set all nodes invisble
                    anchor.getChildren().forEach(node -> node.setVisible(false));
                    //set title and nodes
                    mainLabel.setText("Loading Your Data");
                    mainLabel.setVisible(true);
                    sheridanS.setVisible(true);
                    anchor.setStyle("-fx-background-color: #191970;");
                    mainLabel.setTextFill(Color.WHITE);


                    square1.setVisible(true);
                    square2.setVisible(true);
                    square3.setVisible(true);


                    //set loading animation
                    TranslateTransition animation1 = createAnimationY(square1, -5,Duration.seconds(0.5), true, 2);
                    TranslateTransition animation2 = createAnimationY(square2, -5,Duration.seconds(0.5), true, 2);
                    TranslateTransition animation3 = createAnimationY(square3, -5,Duration.seconds(0.5), true, 2);

                    animation1.setOnFinished(e -> animation2.play());
                    animation2.setOnFinished(e -> animation3.play());
                    animation3.setOnFinished(e-> animation1.play());
                    animation1.setOnFinished(e -> animation2.play());
                    animation2.setOnFinished(e -> animation3.play());
                    animation1.play();

                    //after 7 seconds let the user enter
                    int finalI = i;
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(7), e -> {
                        //after loading time do this:
                        //get Student information
                        System.out.println("Student accessing is: ");
                        System.out.println(studentDB.get(finalI).getName() + " from program " + studentDB.get(finalI).getProgram());

                        //load general database
                        DatabaseController db = new DatabaseController();
                        db.main();

                        //pass the Student object and database instance to the next window
                        try {
                            loadMainWindow(event, studentDB.get(finalI), db);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }) );
                    timeline.setCycleCount(1);
                    timeline.play();


                }

            }
            //if logInSuccess is still false, display error message (means something went wrong)
            if (logInSuccess == false){
                error1.setVisible(true);
            }

        }
        //if the ID does not have the specified lenght and is not numerical
        else {
            //display error message
            error1.setVisible(true);
            logInSuccess = false;
        }

    }

    /**
     * This method checks if a string is made up of only numbers.
     *
     * @param s A String
     * @return true if it is numerical
     * @return false if it is not
     */
    private boolean checkNumerical(String s){
        try {
            Long.parseLong(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    /**
     * This method loads the next window after the user logs in successfully
     *
     * @param e The event that triggers the method (method call)
     * @param s Student object that describes the student that logged in
     * @param db1 An instance of the current database that is being used
     * @throws IOException if file not found
     */
    public void loadMainWindow(ActionEvent e, Student s, DatabaseController db1) throws IOException {

        //get details loader for next window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainCourses.fxml"));
        root = loader.load();

        //create an instance of the controller
        MainTabController mainTabController = loader.getController();
        mainTabController.DisplayCurrentInfo(s, db1);

        //get current stage and then change the scene
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    /**
     * This method loads the window for requesting an account
     * @throws IOException if file not found
     */
    public void loadRequestAccount() throws IOException {
        //get loader for that window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestAnAccount.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 830, 630);
        //get current stage by using a node from it
        Stage stage = (Stage) error1.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private TranslateTransition createAnimationY(Node node, double distance, Duration duration,
                                                Boolean autoReverse, int cycles){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setByY(distance);
        translate.setDuration(duration);
        translate.setCycleCount(cycles);
        translate.setAutoReverse(autoReverse);
        return translate;
    }

}



