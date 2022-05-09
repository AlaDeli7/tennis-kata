package com.elcodeur.enums;

public enum Score {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADV(""),
    DEUCE("");

    private final String scoreValue;

    Score(String scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public String toString() {
        return scoreValue;
    }
}
