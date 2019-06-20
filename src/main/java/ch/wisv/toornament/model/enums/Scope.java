package ch.wisv.toornament.model.enums;

public enum Scope {

    MANAGE("participant:manage_registrations"),
    USER_INFO("user:info"),
    ORGANIZER_VIEW("organizer:view"),
    ORGANIZER_ADMIN("organizer:admin"),
    ORGANIZER_RESULT("organizer:result"),
    ORGANIZER_PARTICIPANT("organizer:participant"),
    ORGANIZER_DELETE("organizer:delete"),
    MANAGE_PARTICIPANTS("participant:manage_participations"),
    MANAGE_REGISTRATIONS("participant:manage_registrations"),
    ORGANIZER_REGISTRATION("organizer:registration");
    private String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public String toString() {
        return scope;
    }
}
