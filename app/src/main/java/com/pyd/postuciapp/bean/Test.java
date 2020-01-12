package com.pyd.postuciapp.bean;

import java.util.Date;

public class Test {

    public enum TestType {
        UNDEFINED(-1),
        ESCALA_IMPACTO_EVENTO(0),
        HAD(1);

        public int value;

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

    public static String getNameByType(TestType type) {
        switch (type) {
            case ESCALA_IMPACTO_EVENTO:
                return "Test de escala de impacto de evento";
            case HAD:
                return "Hospital, ansiedad y depresión (autoadministrada)";
        }

        return "Desconocido";
    }
}
