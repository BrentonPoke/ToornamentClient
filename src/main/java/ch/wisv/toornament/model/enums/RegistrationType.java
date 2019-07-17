package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RegistrationType {
    TEAM("team"), PLAYER("player");
    private String type;
    @JsonValue
    public String getType() {
        return type;
    }

    RegistrationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
