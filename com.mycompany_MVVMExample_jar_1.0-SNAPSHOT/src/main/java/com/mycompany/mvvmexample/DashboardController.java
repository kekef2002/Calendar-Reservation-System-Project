package com.mycompany.mvvmexample;

import com.google.cloud.firestore.Firestore;
import java.time.ZonedDateTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kekef
 */

public class DashboardController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button confirmAppointmentButton;

    private ZonedDateTime selectedDate;

    public void setSelectedDate(ZonedDateTime selectedDate) {
        this.selectedDate = selectedDate;
    }

    @FXML
    private void initialize() {
        confirmAppointmentButton.setOnAction(event -> confirmAppointment());
    }

    private void confirmAppointment() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        CalendarActivity appointment = new CalendarActivity(selectedDate, name, 0);

        FirestoreContext firestoreContext = new FirestoreContext();
        Firestore db = firestoreContext.getFirestore();

        db.collection("appointments").add(appointment).addListener(() -> {
            System.out.println("Appointment saved successfully!");
            navigateTo("CalendarView.fxml", "Calendar View");
        }, Runnable::run);
    }

    private void navigateTo(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmAppointmentButton.getScene().getWindow();
        stage.close();
    }
}
