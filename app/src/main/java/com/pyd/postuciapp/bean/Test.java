package com.pyd.postuciapp.bean;

import java.util.Date;

public class Test {

    private Patient mPatient;
    private Date mTimeOfCompletion;

    Test(Patient mPatient) {
        this.mPatient = mPatient;
        this.mTimeOfCompletion = new Date(System.currentTimeMillis());
    }

    public Patient getPatient() {
        return mPatient;
    }

    public Date getTimeOfCompletion() {
        return mTimeOfCompletion;
    }
}
