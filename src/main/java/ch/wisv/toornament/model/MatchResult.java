package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;

import java.util.List;

public class MatchResult {

    private MatchStatus status;
    private List<Opponent> opponents;

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public List<Opponent> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Opponent> opponents) {
        this.opponents = opponents;
    }
}
