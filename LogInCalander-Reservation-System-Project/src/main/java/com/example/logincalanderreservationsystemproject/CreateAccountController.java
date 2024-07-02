package com.example.logincalanderreservationsystemproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateAccountController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void handleCreateAccount() {
        // Handle account creation logic here
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Creating account for: " + firstName + " " + lastName);
    }
}
