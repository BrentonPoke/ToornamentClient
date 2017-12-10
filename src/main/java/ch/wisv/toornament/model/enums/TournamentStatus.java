package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TournamentStatus {
    @JsonProperty("setup")
    SETUP,
    @JsonProperty("running")
    RUNNING,
    @JsonProperty("completed")
    COMPLETED,
    @JsonProperty("pending")
    PENDING
}
