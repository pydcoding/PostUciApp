package com.pyd.postuciapp.bean;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String dni;
    private Date timeOfCreation;
    private String message;

    public Message(String dni, String message) {
        this.dni = dni;
        this.timeOfCreation = new Date(System.currentTimeMillis());
        this.message = message;
    }

    public String getDni() {
        return dni;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public String getMessage() {
        return message;
    }
}
