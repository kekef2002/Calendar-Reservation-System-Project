/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kekef
 */
public class MainPageController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> handleLoginButton());
        signUpButton.setOnAction(event -> handleSignUpButton());
    }

    private void handleLoginButton() {
        navigateTo("Login.fxml", "Login");
    }

    private void handleSignUpButton() {
        navigateTo("Register.fxml", "Register");
    }

    private void navigateTo(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/group1/" + fxml));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
