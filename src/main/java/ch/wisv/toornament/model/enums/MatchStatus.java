package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchStatus {
    @JsonProperty
    PENDING("pending"),
    @JsonProperty
    RUNNING("running"),
    @JsonProperty
    COMPLETED("completed");

    private String name;
    @JsonValue
    public String getName(){return name;}

    MatchStatus(String name){ this.name = name; }
}
