package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentDetails extends Tournament {

    @JsonProperty("participant_type")
    ParticipantType participantType;
    @JsonProperty("match_type")
    MatchType matchType;
    String organization;
    String website;
    String description;
    String rules;
    String prize;
    @JsonProperty("team_size_min")
    Integer teamSizeMin;
    @JsonProperty("team_size_max")
    Integer teamSizeMax;
    // TODO: Stream model
    Object[] streams;
    @JsonProperty("check_in")
    Boolean checkIn;
    @JsonProperty("participant_nationality")
    Boolean participantNationality;
    @JsonProperty("match_format")
    MatchFormat matchFormat;
}
