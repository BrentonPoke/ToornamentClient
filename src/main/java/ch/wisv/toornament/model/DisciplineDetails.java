package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class DisciplineDetails extends Discipline {

    @JsonProperty("team_size")
    TeamSize teamSize;
    @JsonProperty("additional_fields")
    Map<String, Map<String, String>> additionalFields;

    @Getter
    @Setter
    private class TeamSize {
        int min;
        int max;

        public TeamSize() {
        }

    }
}
