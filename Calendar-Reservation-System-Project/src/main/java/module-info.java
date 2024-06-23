module frank6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens frank6 to javafx.fxml;
    exports frank6;
}
