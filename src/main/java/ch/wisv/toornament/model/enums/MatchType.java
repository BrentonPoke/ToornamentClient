package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchType {
    DUEL("duel"), FFA("ffa"), BYE("bye");

    private String name;

    MatchType(String name) {
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
