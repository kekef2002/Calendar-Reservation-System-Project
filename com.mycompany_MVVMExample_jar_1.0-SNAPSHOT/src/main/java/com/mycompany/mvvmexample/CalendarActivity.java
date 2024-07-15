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
    private String date; // Store date as string in ISO-8601 format
    
    public CalendarActivity() {
    }
    
    public CalendarActivity(ZonedDateTime date, String clientName, Integer serviceNo) {
        this.date = date != null ? date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) : null;
        this.clientName = clientName;
        this.serviceNo = serviceNo;
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

    public Integer getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }
    
    public ZonedDateTime getZonedDateTime() {
        return date != null ? ZonedDateTime.parse(this.date, DateTimeFormatter.ISO_ZONED_DATE_TIME) : null;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", serviceNo=" + serviceNo +
                '}';
    }    
}
