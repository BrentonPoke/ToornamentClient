package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Scope {
    @JsonProperty("participant:manage_registrations")
    MANAGE,
    @JsonProperty("user:info")
    USER_INFO,
    @JsonProperty("organizer:view")
    ORGANIZER_VIEW,
    @JsonProperty("organizer:admin")
    ORGANIZER_ADMIN,
    @JsonProperty("organizer:result")
    ORGANIZER_RESULT,
    @JsonProperty("organizer:participant")
    ORGANIZER_PARTICIPANT,
    @JsonProperty("organizer:delete")
    ORGANIZER_DELETE


}
