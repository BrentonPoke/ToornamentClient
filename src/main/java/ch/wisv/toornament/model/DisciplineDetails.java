package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Map;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class DisciplineDetails extends Discipline {

    @JsonProperty("team_size")
    TeamSize teamSize;
//    @JsonProperty("additional_fields")
//    Map<String, Map<String, String>> additionalFields;
    List<String> platforms_available;

    @Getter
    @Setter
    private class TeamSize {
        int min;
        int max;

        public TeamSize() {
        }

    }
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer(new SimpleDateFormat("yyyy-mm-dd")).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
