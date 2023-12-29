module com.example.javafxfinalproyect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxfinalproyect to javafx.fxml;
    exports com.example.javafxfinalproyect;
    exports com.example.javafxfinalproyect.beans;
    opens com.example.javafxfinalproyect.beans to javafx.fxml;
}