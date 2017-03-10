package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("setup")
    SETUP,
    @JsonProperty("running")
    RUNNING,
    @JsonProperty("completed")
    COMPLETED
}
