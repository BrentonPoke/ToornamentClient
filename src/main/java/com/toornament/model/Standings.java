
package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@EqualsAndHashCode
@SuppressWarnings("unused")
public class Standings {

    private String id;
    private Participant participant;
    private Integer position;
    private Integer rank;
    @JsonProperty("tournament_id")
    private String tournamentID;
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("toString() conversion failed",e);
        }
        return null;
    }
}
