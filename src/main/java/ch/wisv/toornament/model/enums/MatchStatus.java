package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MatchStatus {
    @JsonProperty
    PENDING("pending"),
    @JsonProperty
    RUNNING("running"),
    @JsonProperty
    COMPLETED("completed");

    private String name;
    public String getName(){return name;}

    MatchStatus(String name){ this.name = name; }
}
