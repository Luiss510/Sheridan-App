/**
 * This is the main class of the project that launches the application
 *
 * @author Luis Santos
 *
 *
 */

package com.example.javafxfinalproyect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class CourseApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseApp.class.getResource("logIn.fxml"));
        Scene logScene = new Scene(fxmlLoader.load(), 730 ,550);
        stage.setTitle("Student Access");
        stage.setScene(logScene);
        stage.show();





    }

    public static void main(String[] args) {
        launch();
    }
}

