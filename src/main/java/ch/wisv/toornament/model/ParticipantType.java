package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ParticipantType {
    TEAM("team"), SINGLE("single");

    private String name;

    ParticipantType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
