package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Scope {

    ORGANIZER_VIEW("organizer:view"),
    ORGANIZER_ADMIN("organizer:admin"),
    ORGANIZER_RESULT("organizer:result"),
    ORGANIZER_PARTICIPANT("organizer:participant"),
    ORGANIZER_DELETE("organizer:delete"),
    ORGANIZER_REGISTRATION("organizer:registration"),
    ORGANIZER_PERMISSION("organizer:permission");
    private final String scope;
    @JsonValue
    public String getScope() {
        return scope;
    }

    Scope(String scope) {
        this.scope = scope;
    }

    public String toString() {
        return scope;
    }
}
