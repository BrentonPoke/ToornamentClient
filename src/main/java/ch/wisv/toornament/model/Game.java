package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class Game {
    private int number;
    private MatchStatus status;
    private List<Opponent> opponents;

    private Map<String, Object[]> disciplineFields = new HashMap<>();


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
