package ch.wisv.toornament.model.enums;

public enum TournamentStatus {
    SETUP("setup"),
    RUNNING("running"),
    COMPLETED("completed"),
    PENDING("pending");
    private String status;

    TournamentStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
