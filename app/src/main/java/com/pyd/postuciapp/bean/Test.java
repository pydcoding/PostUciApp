package com.pyd.postuciapp.bean;

import java.util.Date;

public class Test {

    private String dni;
    private Date timeOfCompletion;

    Test(String dni) {
        this.dni = dni;
        this.timeOfCompletion = new Date(System.currentTimeMillis());
    }

    public String getDni() {
        return dni;
    }
    public Date getTimeOfCompletion() {
        return timeOfCompletion;
    }
}
