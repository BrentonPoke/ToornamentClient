package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toornament.model.Custom.CustomFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.Result;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Opponent {
    private Integer number;
    private Participant participant;
    private Result result;
    private Integer rank;
    private Integer score;
    private Boolean forfeit;
    private Integer position;
    @JsonProperty("custom_fields")
    private CustomFields customFields;


    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
