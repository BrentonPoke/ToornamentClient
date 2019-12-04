package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisciplineDetails extends Discipline {

    @JsonProperty("team_size")
    public TeamSize teamSize;
//    @JsonProperty("additional_fields")
//    Map<String, Map<String, String>> additionalFields;
    public List<String> platforms_available;
    public List<Features> features;

    @Getter
    @Setter
    static private class TeamSize {
        int min;
        int max;

        public TeamSize() {
        }

    }
    @Getter
    @Setter
    static private class Features {
        String name;
        String type;
        Object options;
        public Features(){
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
