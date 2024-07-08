/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author kekef
 */

public class DashboardController {

    @FXML
    private Button prevMonthButton;

    @FXML
    private Button nextMonthButton;

    @FXML
    private Label monthYearLabel;

    @FXML
    private ListView<String> timeSlotsListView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button confirmAppointmentButton;

    private ZonedDateTime selectedDate;

    @FXML
    private void initialize() {
        confirmAppointmentButton.setOnAction(event -> confirmAppointment());
    }

    public void setSelectedDate(ZonedDateTime selectedDate) {
        this.selectedDate = selectedDate;
        monthYearLabel.setText(selectedDate.toString());
    }

    private void confirmAppointment() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        // Logic to handle appointment confirmation
        System.out.println("Appointment confirmed for: " + name);
        navigateToCalendarView();
    }

    private void navigateToCalendarView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/group1/CalendarView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Calendar View");
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