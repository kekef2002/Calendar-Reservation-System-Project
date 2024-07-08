module group1.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.google.firebase;
    requires com.google.cloud.firestore;
    requires java.logging;

    opens group1 to javafx.fxml;
    exports group1;
}
