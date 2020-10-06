package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toornament.model.Custom.CustomFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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
            log.error(e.getMessage());
        }
        return null;
    }
}
