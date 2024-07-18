package com.mycompany.mvvmexample;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * FXML Controller class
 *
 * @author group1
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
    private Button signAccountButton;

    @FXML
    private void initialize() {
        FirestoreContext firestoreContext = new FirestoreContext();
        db = firestoreContext.getFirestore();
        registerButton.setOnAction(event -> registerUser());
        signAccountButton.setOnAction(event -> openSignInPage());
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

            ApiFuture<WriteResult> future = db.collection("users").document(username).set(user);
            try {
                future.get();
                System.out.println("User registered successfully!");
                addDefaultSettings(username);
                closeWindow();
                navigateToSignin();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Passwords do not match!");
        }
    }

    private void addDefaultSettings(String username) {
        DocumentReference userRef = db.collection("users").document(username);
        Map<String, Object> defaultSettings = new HashMap<>();
        defaultSettings.put("theme", "light");
        defaultSettings.put("notifications", true);

        ApiFuture<WriteResult> future = userRef.collection("events").document("List of events").set(defaultSettings);
        try {
            future.get();
            System.out.println("Default settings added successfully!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }
    private void openSignInPage() {
        closeWindow();
        navigateToSignin();
    }

    private void navigateToSignin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/mvvmexample/Signin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sign In");
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
