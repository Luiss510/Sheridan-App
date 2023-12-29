/**
 * This class controls the FXML template that displays the window that lets the user request an account
 * it takes user input for its mail and student id.
 *
 * @author Luis Santos
 *
 */

package com.example.javafxfinalproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RequestAccountController {
    @FXML
    private TextField studentID;

    @FXML
    private TextField academicMail;

    @FXML
    private Button submit;

    @FXML
    private Label success;

    @FXML
    private Parent root;

    @FXML
    private Scene scene;


    /**
     * This method handeles the submit button of the form
     * @param event what triggered the method (button click)
     */
    public void handleSubmission(ActionEvent event) throws IOException {
        String id = studentID.getText();
        String mail = academicMail.getText();

        System.out.println("Student with mail " + mail + " and studentID " + id + " Requested an account");
        success.setVisible(true);

        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();

        //load main courses again
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 730, 550);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method handles the go back button
     * sends the user back to the log in window
     *
     */
    public void goBackToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();

        //load main courses again
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 730, 550);
        stage.setScene(scene);
        stage.show();
    }
}
