package com.mycompany.mvvmexample;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalendarViewController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private TabPane mainTabPane, sideTabPane;

    @FXML
    private Tab calenderView, dashboard;

    @FXML
    private Tab todayTab, menu, contactInfo, privacyPolicy;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @FXML
    private Button dashboardButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField timeField;

    @FXML
    private TextArea notesField;

    @FXML
    private Button confirmAppointmentButton;

    private Firestore db;
    private String loggedInUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FirestoreContext firestoreContext = new FirestoreContext();
        db = firestoreContext.getFirestore();
        loggedInUser = "sampleUser"; // Replace this with actual user retrieval logic
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        try {
            drawCalendar();
        } catch (ExecutionException ex) {
            Logger.getLogger(CalendarViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmAppointmentButton.setOnAction(event -> confirmAppointment());
    }

    @FXML
    void backOneMonth(ActionEvent event) throws ExecutionException {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) throws ExecutionException {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() throws ExecutionException {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                stackPane.setOnMouseClicked(event -> navigateToDashboard());
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        calendarActivityBox.getStyleClass().add("calendar-activity-box");
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    System.out.println(calendarActivities);
                });
                break;
            }
            CalendarActivity activity = calendarActivities.get(k);
            Text text = new Text(activity.getClientName() + ", " + activity.getTime()); // Display time as string
            text.getStyleClass().add("text");
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            ZonedDateTime zonedDateTime = activity.getZonedDateTime();
            if (zonedDateTime != null) {
                int activityDate = zonedDateTime.getDayOfMonth();
                if (!calendarActivityMap.containsKey(activityDate)) {
                    calendarActivityMap.put(activityDate, new ArrayList<>(List.of(activity)));
                } else {
                    List<CalendarActivity> oldListByDate = calendarActivityMap.get(activityDate);
                    oldListByDate.add(activity);
                    calendarActivityMap.put(activityDate, oldListByDate);
                }
            } else {
                System.err.println("CalendarActivity has null date: " + activity);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) throws ExecutionException {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        FirestoreContext firestoreContext = new FirestoreContext();
        Firestore db = firestoreContext.getFirestore();

        try {
            ApiFuture<QuerySnapshot> future = db.collection("appointments")
                                                .whereEqualTo("user", loggedInUser)
                                                .get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> data = document.getData();
                String date = (String) data.get("date");
                String clientName = (String) data.get("clientName");
                String notes = (String) data.get("notes");
                String user = (String) data.get("user");
                String time = (String) data.get("time");

                CalendarActivity activity = new CalendarActivity(
                    ZonedDateTime.parse(date, DateTimeFormatter.ISO_ZONED_DATE_TIME),
                    clientName,
                    notes,
                    user,
                    time
                );
                calendarActivities.add(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createCalendarMap(calendarActivities);
    }

    private void navigateTo(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToDashboard() {
        mainTabPane.getSelectionModel().select(dashboard);
    }

    private void closeWindow() {
        Stage stage = (Stage) dashboardButton.getScene().getWindow();
        stage.close();
    }

    private void confirmAppointment() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        ZonedDateTime selectedDate = dateField.getValue().atStartOfDay(ZoneId.systemDefault());
        String notes = notesField.getText();
        String time = timeField.getText();

        CalendarActivity appointment = new CalendarActivity(selectedDate, name, notes, loggedInUser, time);

        Map<String, Object> appointmentData = new HashMap<>();
        appointmentData.put("date", appointment.getDate());
        appointmentData.put("clientName", appointment.getClientName());
        appointmentData.put("notes", appointment.getNotes());
        appointmentData.put("user", appointment.getUser());
        appointmentData.put("time", appointment.getTime());

        ApiFuture<DocumentReference> future = db.collection("appointments").add(appointmentData);
        try {
            future.get();
            System.out.println("Appointment saved successfully!");
            Platform.runLater(() -> navigateTo("CalendarView.fxml", "Calendar View"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToCalendarView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CalendarView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Calendar View");
            stage.setScene(new Scene(root, 1000, 650)); // Set size here
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuButton(ActionEvent actionEvent) {
        sideTabPane.getSelectionModel().select(menu);
    }

    public void contactInfoButton(ActionEvent actionEvent) {
        sideTabPane.getSelectionModel().select(contactInfo);
    }

    public void privacyPolicyButton(ActionEvent actionEvent) {
        sideTabPane.getSelectionModel().select(privacyPolicy);
    }

    public void signoutButton(ActionEvent actionEvent) {

    }

    public void todaytabbutton(ActionEvent actionEvent) {
        sideTabPane.getSelectionModel().select(todayTab);
    }
}
