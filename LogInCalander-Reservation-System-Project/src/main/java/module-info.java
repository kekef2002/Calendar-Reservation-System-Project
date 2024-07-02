module com.example.logincalanderreservationsystemproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.logincalanderreservationsystemproject to javafx.fxml;
    exports com.example.logincalanderreservationsystemproject;
}