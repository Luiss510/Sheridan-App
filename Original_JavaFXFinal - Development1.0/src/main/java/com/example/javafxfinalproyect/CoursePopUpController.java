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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CoursePopUpController {

    @FXML
    private Label courseName;

    @FXML
    private Label courseCode;


    @FXML
    private Button closeButton;

    @FXML
    private Button enrollButton;

    @FXML
    private Label error1;

    @FXML
    private Text courseDescription;

    @FXML
    private ComboBox dropMenu;

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
        courseName.setText(c.getName());
        courseDescription.setText(c.getDescription());
        courseCode.setText("Course code: " + c.getCourseCode());


        Schedule schedule;
        ArrayList<Schedule> currentSchedules = new ArrayList<>();

        //SET UP DROP DOWN MENU
        //get professors and times available, go through each schedule
        for(int i = 0; i < db.schedules.size(); i++){
            //if schedule matches the course
            if(db.schedules.get(i).getCourse() == course){
                schedule = db.schedules.get(i);
                //add to drop menu
                dropMenu.getItems().add(schedule.toString());
                currentSchedules.add(schedule);

            }
        }


        dropMenu.setOnAction(event -> {
            //get selected schedule by student
            String selectedOption = (String) dropMenu.getSelectionModel().getSelectedItem();
            //get the index
            int selectedOptionIndex = dropMenu.getSelectionModel().getSelectedIndex();

            //if the option is not null, make active the enroll button
            if (selectedOption != null){
                enrollButton.setDisable(false);
            }
            student.addSchedule(currentSchedules.get(selectedOptionIndex));
        });



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

    @FXML
    private void handleChoiceBox(ActionEvent event){

        error1.setVisible(true);
    }

    /**
     * This method enrolls the student in the course
     *
     */
    @FXML
    private void handleEnrollButton(ActionEvent event) throws IOException {

        //get programs from database
        ArrayList<Program> programs = db.programs;

        //go through program
        for (int i = 0; i < programs.size(); i++){
            //for each program get list of courses
            ArrayList<Course> programCourses = programs.get(i).getCourses();

            //for each of the courses, check if it matches the current course student is trying to enroll
            for(int y = 0; y < programCourses.size(); y++){
                //if found enroll student
                if(programCourses.get(y).getName().equals(course.getName())){
                    System.out.println("Course found");
                    programs.get(i).getCourses().get(y).enrollStudent(student);
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

    }
}
