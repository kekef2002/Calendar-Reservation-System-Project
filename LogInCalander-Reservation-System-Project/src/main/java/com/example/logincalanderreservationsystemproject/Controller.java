package com.example.logincalanderreservationsystemproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Hyperlink createAccountLink;

    @FXML
    private void handleCreateAccountLink() throws IOException {
        Stage stage = (Stage) createAccountLink.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/logincalanderreservationsystemproject/create_account.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
