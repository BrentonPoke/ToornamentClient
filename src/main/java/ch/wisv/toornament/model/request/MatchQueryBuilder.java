package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.enums.MatchSort;

import java.util.HashMap;
import java.util.Map;

public class MatchQueryBuilder {

    private boolean hasResult = false;
    private Integer stageNumber;
    private Integer groupNumber;
    private Integer roundNumber;
    private MatchSort sort = MatchSort.STRUCTURE;
    private String participantId;
    private boolean withGames = false;

    public MatchQueryBuilder withResult(boolean hasResult) {
        this.hasResult = hasResult;
        return this;
    }

    public MatchQueryBuilder stageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
        return this;
    }

    public MatchQueryBuilder groupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public MatchQueryBuilder roundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
        return this;
    }

    public MatchQueryBuilder sort(MatchSort sort) {
        this.sort = sort;
        return this;
    }

    public MatchQueryBuilder participant(String participantId) {
        this.participantId = participantId;
        return this;
    }

    public MatchQueryBuilder withGames(boolean withGames) {
        this.withGames = withGames;
        return this;
    }

    public Map<String, String> build() {
        Map<String, String> params = new HashMap<>();

        params.put("has_result", hasResult ? "1" : "0");
        params.put("sort", sort.getName());
        params.put("with_games", withGames ? "1" : "0");

        if (stageNumber != null) {
            params.put("stage_number", String.valueOf(stageNumber));
        }
        if (groupNumber != null) {
            params.put("group_number", String.valueOf(groupNumber));
        }
        if (roundNumber != null) {
            params.put("round_number", String.valueOf(roundNumber));
        }
        if (participantId != null) {
            params.put("participant_id", participantId);
        }

        return params;
    }
}
