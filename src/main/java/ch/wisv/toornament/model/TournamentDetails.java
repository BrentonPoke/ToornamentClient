package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.MatchType;
import ch.wisv.toornament.model.enums.ParticipantType;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class TournamentDetails extends Tournament {

    @JsonProperty("participant_type")
    private ParticipantType participantType;
    @JsonProperty("match_type")
    private MatchType matchType;
    private String organization;
    private String website;
    private String description;
    private String rules;
    private String prize;
    @JsonProperty("team_min_size")
    private Integer teamSizeMin;
    @JsonProperty("team_max_size")
    private Integer teamSizeMax;
    // TODO: Stream model
    private Object[] streams;
    @JsonProperty("check_in")
    private Boolean checkIn;
    @JsonProperty("participant_nationality")
    private Boolean participantNationality;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;
    private String timezone;

    private Map<String, Object[]> disciplineFields = new HashMap<>();

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Integer getTeamSizeMin() {
        return teamSizeMin;
    }

    public void setTeamSizeMin(Integer teamSizeMin) {
        this.teamSizeMin = teamSizeMin;
    }

    public Integer getTeamSizeMax() {
        return teamSizeMax;
    }

    public void setTeamSizeMax(Integer teamSizeMax) {
        this.teamSizeMax = teamSizeMax;
    }

    public Object[] getStreams() {
        return streams;
    }

    public void setStreams(Object[] streams) {
        this.streams = streams;
    }

    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getParticipantNationality() {
        return participantNationality;
    }

    public void setParticipantNationality(Boolean participantNationality) {
        this.participantNationality = participantNationality;
    }

    public MatchFormat getMatchFormat() {
        return matchFormat;
    }

    public void setMatchFormat(MatchFormat matchFormat) {
        this.matchFormat = matchFormat;
    }

    public Map<String, Object[]> getDisciplineFields() {
        return disciplineFields;
    }

    public void setDisciplineFields(Map<String, Object[]> disciplineFields) {
        this.disciplineFields = disciplineFields;
    }

    // Capture all other fields that Jackson do not match other members
    @JsonAnyGetter
    public Map<String, Object[]> otherFields() {
        return disciplineFields;
    }

    @JsonAnySetter
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public void setOtherField(String name, Object[] value) {
        disciplineFields.put(name, value);
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
