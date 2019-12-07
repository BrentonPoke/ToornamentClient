package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventName {

    REGISTRATION_CREATED("registration.created"),
    REGISTRATION_INFO_UPDATED("registration.info_updated"),
    REGISTRATION_ACCEPTED("registration.accepted"),
    REGISTRATION_REFUSED("registration.refused"),
    REGISTRATION_RESET("registration.reset"),
    REGISTRATION_CANCELLED("registration.cancelled"),
    REGISTRATION_DELETED("registration.deleted"),
    PARTICIPANT_CHECKED_IN("participant.checked_in"),
    PARTICIPANT_UNCHECKED_IN("participant.unchecked_in"),
    PARTICIPANT_CREATED("participant.created"),
    PARTICIPANT_DELETED("participant.deleted"),
    PARTICIPANT_INFO_UPDATED("participant.info_updated"),
    TOURNAMENT_REGISTRATION_OPENED("tournament.registration_opened"),
    TOURNAMENT_REGISTRATION_CLOSED("tournament.registration_closed"),
    TOURNAMENT_CHECK_IN_OPENED("tournament.check_in_opened"),
    TOURNAMENT_CHECK_IN_CLOSED("tournament.check_in_closed");

    private String name;
    EventName(String name){
        this.name = name;
    }
    @JsonValue
    public String getName() {
        return name;
    }
}
