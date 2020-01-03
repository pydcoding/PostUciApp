package com.pyd.postuciapp.bean;

import java.util.Date;

public class Test {

    public enum TestType {
        ESCALA_IMPACTO_EVENTO(0);

        public final int value;

        TestType(int value) {
            this.value = value;
        }
    }

    private TestType id;
    private String dni;
    private Date timeOfCompletion;

    Test(TestType id, String dni) {
        this.dni = dni;
        this.timeOfCompletion = new Date(System.currentTimeMillis());
    }

    public TestType getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public Date getTimeOfCompletion() {
        return timeOfCompletion;
    }
}
