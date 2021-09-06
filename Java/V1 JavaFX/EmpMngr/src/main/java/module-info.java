module com.example.empmngr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;
    requires mongo.java.driver;
    requires json.simple;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires java.instrument;


    opens com.example.empmngr to javafx.fxml;
    exports com.example.empmngr;
}