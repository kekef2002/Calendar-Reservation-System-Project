package com.mycompany.mvvmexample;

import com.google.cloud.firestore.Firestore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kekef
 */

public class App extends Application {

    private Firestore db;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FirestoreContext firestoreContext = new FirestoreContext();
        db = firestoreContext.getFirestore();

        Parent root = FXMLLoader.load(getClass().getResource("CalendarView.fxml"));
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
