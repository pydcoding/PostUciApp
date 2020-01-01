package com.pyd.postuciapp.bean;

import java.util.Map;

public class TestEscalaImpactoEvento extends Test {

    public enum Answer {
        NEVER(0),
        RARELY(1),
        SOMETIMES(3),
        OFTEN(5);

        public final int points;

        Answer(int points) {
            this.points = points;
        }
    }

    private Map<Integer, Answer> mAnswers;

    public TestEscalaImpactoEvento(Patient patient, Map<Integer, Answer> answers) {
        super(patient);

        this.mAnswers = answers;
    }

    public Map<Integer, Answer> getAnswers() {
        return this.mAnswers;
    }
}
