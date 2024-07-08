/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import com.google.cloud.firestore.Firestore;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


/**
 * FXML Controller class
 *
 * @author kekef
 */
public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    private Firestore db;

    @FXML
    private void initialize() {
        db = FirestoreContext.getFirestore();
        registerButton.setOnAction(event -> registerUser());
    }

    private void registerUser() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.equals(confirmPassword)) {
            Map<String, Object> user = new HashMap<>();
            user.put("firstName", firstName);
            user.put("lastName", lastName);
            user.put("username", username);
            user.put("email", email);
            user.put("password", password);

            db.collection("users").document(username).set(user).addOnSuccessListener(aVoid -> {
                System.out.println("User registered successfully!");
                closeWindow();
            }).addOnFailureListener(e -> {
                e.printStackTrace();
            });
        } else {
            System.out.println("Passwords do not match!");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }
}