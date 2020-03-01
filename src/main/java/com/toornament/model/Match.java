package com.toornament.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.toornament.model.enums.MatchFormat;
import com.toornament.model.enums.MatchStatus;
import com.toornament.model.enums.MatchType;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(value = Include.NON_NULL)
public class Match {

    private String id;
    private MatchType type;
    private String discipline;
    private MatchStatus status;
    @JsonProperty("tournament_id")
    private String tournamentId;
    private Integer number;
    @JsonProperty("stage_id")
    private String stageNumber;
    @JsonProperty("group_id")
    private String groupNumber;
    @JsonProperty("round_id")
    private String roundNumber;
    @JsonProperty("scheduled_datetime")
    private String date;
    private String timezone;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;
    private List<Opponent> opponents;
    private Object settings;
    @JsonProperty("played_at")
    private String playedAt;
    @JsonProperty("report_closed")
    private Boolean reportClosed;



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
