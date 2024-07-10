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

    @FXML
    private Button cancelAppointmentButton;

    private ZonedDateTime selectedDate;

    @FXML
    private void initialize() {
        confirmAppointmentButton.setOnAction(event -> saveAppointment());
        cancelAppointmentButton.setOnAction(event -> cancelAppointment());
    }

    public void setSelectedDate(ZonedDateTime selectedDate) {
        this.selectedDate = selectedDate;
    }

    private void saveAppointment() {
        // Save appointment logic here

        closeWindow();
        navigateToCalendarView();
    }

    private void cancelAppointment() {
        // Cancel appointment logic here

        closeWindow();
        navigateToCalendarView();
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmAppointmentButton.getScene().getWindow();
        stage.close();
    }

    private void navigateToCalendarView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/mvvmexample/CalendarView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Calendar View");
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}