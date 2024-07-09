package com.mycompany.mvvmexample;

import com.google.api.core.ApiFuture;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

/**
 * FXML Controller class
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private Firestore db;

    @FXML
    private void initialize() {
        FirestoreContext firestoreContext = new FirestoreContext();
        db = firestoreContext.getFirestore();
        loginButton.setOnAction(event -> loginUser());
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ApiFuture<DocumentSnapshot> future = db.collection("users").document(username).get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists() && document.getString("password").equals(password)) {
                System.out.println("User logged in successfully!");
                navigateTo("CalendarView.fxml", "Calendar View");
            } else {
                System.out.println("Invalid username or password!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
