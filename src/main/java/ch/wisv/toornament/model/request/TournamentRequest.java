package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.MatchFormat;
import ch.wisv.toornament.model.ParticipantType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TournamentRequest {

    private String discipline;
    private String name;
    private Integer size;
    @JsonProperty("participant_type")
    private ParticipantType participantType;
    @JsonProperty("full_name")
    private String fullName;
    private String organization;
    private String website;
    @JsonProperty("date_start")
    private Date dateStart;
    @JsonProperty("date_end")
    private Date dateEnd;
    private String timezone;
    private Boolean online;
    @JsonProperty("public")
    private Boolean isPublic;
    private String location;
    private String country;
    private String description;
    private String rules;
    private String prize;
    @JsonProperty("check_in")
    private Boolean checkIn;
    @JsonProperty("participant_nationality")
    private Boolean participantNationality;
    @JsonProperty("match_format")
    private MatchFormat matchFormat;

    public TournamentRequest(String discipline, String name, Integer size, ParticipantType participantType) {
        this(discipline, name, size, participantType, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public TournamentRequest(String discipline, String name, Integer size, ParticipantType participantType, String fullName, String organization, String website, Date dateStart, Date dateEnd, String timezone, Boolean online, Boolean isPublic, String location, String country, String description, String rules, String prize, Boolean checkIn, Boolean participantNationality, MatchFormat matchFormat) {
        this.discipline = discipline;
        this.name = name;
        this.size = size;
        this.participantType = participantType;
        this.fullName = fullName;
        this.organization = organization;
        this.website = website;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timezone = timezone;
        this.online = online;
        this.isPublic = isPublic;
        this.location = location;
        this.country = country;
        this.description = description;
        this.rules = rules;
        this.prize = prize;
        this.checkIn = checkIn;
        this.participantNationality = participantNationality;
        this.matchFormat = matchFormat;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}
