package com.via.mapmanager.rest.datatype;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ErrorResponse {

    private HttpStatus statusCode;
    private String message;
    private Date timestamp;

    public ErrorResponse(HttpStatus statusCode, String message,Date date) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = date;
    }
    public HttpStatus getStatusCode() {
            return statusCode;
        }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

