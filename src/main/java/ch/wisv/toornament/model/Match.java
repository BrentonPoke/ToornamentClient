package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Custom.CustomFields;
import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.MatchStatus;
import ch.wisv.toornament.model.enums.MatchType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Match {

    private String id;
    private MatchType type;
    private String discipline;
    private MatchStatus status;
    @JsonProperty("tournament_id")
    private String tournamentId;
    private BigInteger number;
    @JsonProperty("stage_number")
    private BigInteger stageNumber;
    @JsonProperty("group_number")
    private BigInteger groupNumber;
    @JsonProperty("round_number")
    private BigInteger roundNumber;
    private Date date;
    private String timezone;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;
    private List<Opponent> opponents;
    private List<Game> games;
    private Date played_at;

    public Date getPlayed_at() {
        return played_at;
    }

    public void setPlayed_at(Date played_at) {
        this.played_at = played_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MatchType getType() {
        return type;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public BigInteger getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(BigInteger stageNumber) {
        this.stageNumber = stageNumber;
    }

    @JsonSetter("stage_id")
    public void setStageId(BigInteger stageNumber) {
        this.stageNumber = stageNumber;
    }

    public BigInteger getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(BigInteger groupNumber) {
        this.groupNumber = groupNumber;
    }

    @JsonSetter("group_id")
    public void setGroupId(BigInteger groupNumber) {
        this.groupNumber = groupNumber;
    }

    public BigInteger getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(BigInteger roundNumber) {
        this.roundNumber = roundNumber;
    }

    @JsonSetter("round_id")
    public void setRoundId(BigInteger roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonSetter("scheduled_datetime")
    public void setDatetime(Date date){this.date=date;}

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public MatchFormat getMatchFormat() {
        return matchFormat;
    }

    public void setMatchFormat(MatchFormat matchFormat) {
        this.matchFormat = matchFormat;
    }

    public List<Opponent> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Opponent> opponents) {
        this.opponents = opponents;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

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
