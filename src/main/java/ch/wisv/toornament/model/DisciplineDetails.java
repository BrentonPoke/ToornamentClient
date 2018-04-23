package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class DisciplineDetails extends Discipline {

    @JsonProperty("team_size")
    TeamSize teamSize;
    @JsonProperty("additional_fields")
    Map<String, Map<String, String>> additionalFields;

    public TeamSize getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(TeamSize teamSize) {
        this.teamSize = teamSize;
    }

    public Map<String, Map<String, String>> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(Map<String, Map<String, String>> additionalFields) {
        this.additionalFields = additionalFields;
    }

    private class TeamSize {
        int min;
        int max;

        public TeamSize() {
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

    }
}
