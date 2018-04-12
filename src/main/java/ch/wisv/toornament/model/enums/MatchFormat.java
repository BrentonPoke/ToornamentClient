package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchFormat {
    NONE("none"), ONE("one"), HOME_AWAY("home_away"), BO3("bo3"), BO5("bo5"), BO7("bo7"), BO9("bo9"), BO11("bo11");

    private String name;

    MatchFormat(String name) {
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
