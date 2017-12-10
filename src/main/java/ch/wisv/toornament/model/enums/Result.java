package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Result {
    UNKNOWN,
    WIN,
    DRAW,
    LOSS;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
