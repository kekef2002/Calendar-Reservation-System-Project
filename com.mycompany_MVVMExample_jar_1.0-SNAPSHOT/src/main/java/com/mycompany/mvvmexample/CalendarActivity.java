/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvvmexample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kekef
 */

public class CalendarActivity {
//    private ZonedDateTime date;
    private String clientName;
    private Integer serviceNo;
    private String email;
    private String phoneNumber;
    private ZonedDateTime date; // Store date as string in ISO-8601 format

    public CalendarActivity() {
    }

    public CalendarActivity(ZonedDateTime date, String clientName, Integer serviceNo , String email, String phoneNumber) {
        this.date = date ;
        this.clientName = clientName;
        this.serviceNo = serviceNo;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }

    public ZonedDateTime getZonedDateTime() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", serviceNo=" + serviceNo + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }    
}
