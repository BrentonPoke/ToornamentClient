package com.toornament.model;


import com.toornament.model.enums.MatchFormat;
import com.toornament.model.enums.MatchStatus;
import com.toornament.model.enums.MatchType;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Match {

    private String id;
    private MatchType type;
    private String discipline;
    private MatchStatus status;
    @JsonProperty("tournament_id")
    private String tournamentId;
    private Long number;
    @JsonAlias({"stage_number","stage_id"})
    private Long stageNumber;
    @JsonAlias({"group_number","group_id"})
    private Long groupNumber;
    @JsonAlias({"round_number","round_id"})
    private Long roundNumber;
    @JsonAlias({"date","scheduled_datetime"})
    private LocalDateTime date;
    private String timezone;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;
    private List<Opponent> opponents;
    private Object settings;
    private LocalDateTime played_at;
    private boolean report_closed;



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
