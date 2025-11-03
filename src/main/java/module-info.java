<<<<<<< HEAD
module desctopapp.workshop1_miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;
    requires javafx.graphics;


    opens desctopapp.workshop1_miniproject3 to javafx.fxml;
    exports desctopapp.workshop1_miniproject3;
=======
module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
>>>>>>> 1dfdf14 (new mod)
}