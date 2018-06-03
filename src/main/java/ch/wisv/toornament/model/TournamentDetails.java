package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.MatchType;
import ch.wisv.toornament.model.enums.ParticipantType;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class TournamentDetails extends Tournament {

    @JsonProperty("participant_type")
    private ParticipantType participantType;
    @JsonProperty("match_type")
    private MatchType matchType;
    private String organization;
    private String website;
    private String description;
    private String rules;
    private String prize;
    @JsonProperty("team_min_size")
    private Integer teamSizeMin;
    @JsonProperty("team_max_size")
    private Integer teamSizeMax;
    @JsonProperty("check_in")
    private Boolean checkIn;
    @JsonProperty("participant_nationality")
    private Boolean participantNationality;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;
    private String timezone;
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
