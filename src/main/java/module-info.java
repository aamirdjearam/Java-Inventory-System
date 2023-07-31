module com.example.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens main to javafx.fxml;
    exports main;
    exports controller;
    opens controller to javafx.fxml;
    opens model to javafx.base, javafx.fxml;
    exports model;
}