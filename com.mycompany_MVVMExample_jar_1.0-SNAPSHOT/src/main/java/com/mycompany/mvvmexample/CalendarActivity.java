package com.mycompany.mvvmexample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author group1
 */

/**
 * Class representing a calendar activity (event).
 */
public class CalendarActivity {
    private String date;
    private String clientName;
    private String notes;
    private String user;
    private String time;

    public CalendarActivity() {}

    public CalendarActivity(ZonedDateTime date, String clientName, String notes, String user, String time) {
        this.date = date != null ? date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) : null;
        this.clientName = clientName;
        this.notes = notes;
        this.user = user;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date != null ? date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) : null;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ZonedDateTime getZonedDateTime() {
        return date != null ? ZonedDateTime.parse(this.date, DateTimeFormatter.ISO_ZONED_DATE_TIME) : null;
    }

    @Override
    public String toString() {
        return "CalendarActivity{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", notes='" + notes + '\'' +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
