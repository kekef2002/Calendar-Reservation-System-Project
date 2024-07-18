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
 * @author group1
 */

public class App extends Application {

    private Firestore db;

@Override
public void start(Stage primaryStage) throws Exception {
    FirestoreContext firestoreContext = new FirestoreContext();
    db = firestoreContext.getFirestore();

    Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
    primaryStage.setTitle("Signin Page");
    primaryStage.setScene(new Scene(root)); // Signin.fxml size
    primaryStage.show();
}

private void navigateToCalendarView() {
    try {
        Parent calendarRoot = FXMLLoader.load(getClass().getResource("CalendarView.fxml"));
        Stage calendarStage = new Stage();
        calendarStage.setTitle("Calendar View");
        calendarStage.setScene(new Scene(calendarRoot, 1000, 650)); // Set size here
        calendarStage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        launch(args);
    }
}
