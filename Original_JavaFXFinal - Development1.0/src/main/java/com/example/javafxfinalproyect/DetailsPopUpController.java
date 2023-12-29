
/**
 * This class controls the FXML template that displays the preview of a course and offers the option to enroll
 *
 * @author Luis Santos
 *
 */
package com.example.javafxfinalproyect;

import com.example.javafxfinalproyect.beans.Course;
import com.example.javafxfinalproyect.beans.Program;
import com.example.javafxfinalproyect.beans.Schedule;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class DetailsPopUpController {

    @FXML
    private Label courseName;

    @FXML
    private Label courseCode;

    @FXML
    private ImageView courseImage;

    @FXML
    private Label studentName;

    @FXML
    private Label dates;

    @FXML
    private Label size;

    @FXML
    private Label instructor;


    @FXML
    private Button closeButton;


    @FXML
    private Text courseDescription;

    @FXML
    private Text courseSchedule;

    private Course course;

    private Student student;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private Label previousWindowNode;

    private DatabaseController db;


    /**
     * This method displays the details of the course
     *
     * @param s The current student logged in (information passed from the previous window)
     * @param db1 Current instance of the database being used (information passed from the previous window)
     * @param c current course being displayed (information passed from the previous window)
     * @param previousWindow any node from the previous window to allow closing(information passed from the previous window)
     */
    public void displayCourseDetails(Course c, Student s, Label previousWindow, DatabaseController db1){
        //get input from the last window and assign them to the local variables (to enable access for any method in the class)
        db = db1;
        previousWindowNode = previousWindow;
        course = c;
        student = s;

        courseName.setText(course.getName());
        courseDescription.setText(course.getDescription());
        courseCode.setText("Course code: " + course.getCourseCode());
        Image image = new Image(new File(course.getImageURL()).toURI().toString());
        courseImage.setImage(image);
        studentName.setText("Student Name: " + s.getName());
        size.setText("Class Size: " + course.getEnrolledStudents().size() + "/35");


        for(int i = 0; i < s.getSchedule().size(); i++){
            if(student.getSchedule().get(i).getCourse() == course){
                instructor.setText("Instructor: " + student.getSchedule().get(i).getInstructor().getName());
                courseSchedule.setText(student.getSchedule().get(i).toString());
            }
        }




    }

    /**
     * This method displays the details closes the current window
     * @param event current event that triggered the method
     */
    @FXML
    private void handleCloseButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method activates if the user tries to access a feature that is not yet implemented
     * @param event current event that triggered the method
     */


    /**
     * This method drops the student from the course
     * @param event current event that triggered the method
     */
    public void handleDropButton(ActionEvent event){

        //display an alert for the user to confirm if he wants to log out or not
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention!!!");
        alert.setHeaderText("Are you sure you want to drop this course?!");
        alert.setContentText("Dropping a course could have academic and monetary penalties.\n" +
                "We recommend talking with you academic advisor prior making this decision");
        Optional<ButtonType> result = alert.showAndWait();

        //depending on the answer, go back to the Log In window
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                //get programs from database
                ArrayList<Program> programs = db.programs;

                //go through program
                for (int i = 0; i < programs.size(); i++){
                    //for each program get list of courses
                    ArrayList<Course> programCourses = programs.get(i).getCourses();

                    //for each of the courses, check if it matches the current course student is trying to drop
                    for(int y = 0; y < programCourses.size(); y++){
                        //if found drop student
                        if(programCourses.get(y).getName().equals(course.getName())){
                            System.out.println("Course found");
                            programs.get(i).getCourses().get(y).dropStudent(student);
                        }
                    }
                }

                //go back to main courses
                //close current scene
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();

                stage = (Stage) previousWindowNode.getScene().getWindow();
                stage.close();

                //load main courses again
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainCourses.fxml"));
                root = loader.load();

                MainTabController mainTabController = loader.getController();
                mainTabController.DisplayCurrentInfo(student, db);


                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

