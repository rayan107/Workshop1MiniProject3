module desctopapp.workshop1_miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;
    requires javafx.graphics;


    opens desctopapp.workshop1_miniproject3 to javafx.fxml;
    exports desctopapp.workshop1_miniproject3;
}