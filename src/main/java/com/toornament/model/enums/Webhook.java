package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;


public enum Webhook {
    REGISTRATION_CREATED("registration.created"),
    REGISTRATION_INFO_UPDATED("registration.info_updated"),
    REGISTRATION_ACCEPTED("registration.accepted"),
    REGISTRATION_REFUSED("registration.refused"),
    REGISTRATION_RESET("registration.reset"),
    REGISTRATION_CANCELLED("registration.cancelled"),
    REGISTRATION_DELETED("registration.deleted"),

    PARTICIPANT_CREATED("participant.created"),
    PARTICIPANT_INFO_UPDATED("participant.info_updated"),
    PARTICIPANT_CHECKED_IN("participant.checked_in"),
    PARTICIPANT_UNCHECKED_IN("participant.unchecked_in"),
    PARTICIPANT_DELETED("participant.deleted"),

    TOURNAMENT_REGISTRATION_OPENED("tournament.registration_opened"),
    TOURNAMENT_REGISTRATION_CLOSED("tournament.registration_closed"),
    TOURNAMENT_CHECKED_IN_OPEN("tournament.check_in_opened"),
    TOURNAMENT_CHECKED_IN_CLOSED("tournament.check_in_closed")
    ;
    private String name;
    @JsonValue
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    Webhook(String name) {
        this.name = name;
    }
}
