package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TournamentStatus {
    SETUP("setup"),
    RUNNING("running"),
    COMPLETED("completed"),
    PENDING("pending");
    private String status;
    @JsonValue
    public String getStatus() {
        return status;
    }

    TournamentStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
