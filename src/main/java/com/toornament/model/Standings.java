
package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@SuppressWarnings("unused")
public class Standings {

    private String id;
    private Participant participant;
    private Integer position;
    private Integer rank;
    @JsonProperty("tournament_id")
    private String tournamentId;

}
