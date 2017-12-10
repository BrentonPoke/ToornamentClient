package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private int number;
    private MatchStatus status;
    private List<Opponent> opponents;

    private Map<String, Object[]> disciplineFields = new HashMap<>();


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    // Capture all other fields that Jackson do not match other members
    @JsonAnyGetter
    public Map<String, Object[]> otherFields() {
        return disciplineFields;
    }

    @JsonAnySetter
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public void setOtherField(String name, Object[] value) {
        disciplineFields.put(name, value);
    }
}
