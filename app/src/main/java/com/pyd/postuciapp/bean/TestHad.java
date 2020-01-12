package com.pyd.postuciapp.bean;

import java.util.Map;

public class TestHad extends Test {

    public enum Answer {
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3);

        public final int points;

        Answer(int points) {
            this.points = points;
        }
    }

    private Map<String, Answer> answers;

    public TestHad(TestType id, String dni, Map<String, Answer> answers) {
        super(id, dni);

        this.answers = answers;
    }

    public Map<String, Answer> getAnswers() {
        return this.answers;
    }
}
