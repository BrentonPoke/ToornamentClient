package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.TournamentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;

public class Tournament {

    private String id;
    private String discipline;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private Date scheduled_date_start;
    private Date scheduled_date_end;
    private TournamentStatus status;
    @JsonProperty("date_start")
    private Date dateStart;
    @JsonProperty("date_end")
    private Date dateEnd;
    private Boolean online;
    @JsonProperty("public")
    private Boolean isPublic;
    private Boolean archived;
    private String location;
    private String country;
    private Integer size;
    private Logo logo;
    private ArrayList<String> platforms;
    private String registration_enabled;
    private Date registration_opening_datetime;
    private Date registration_closing_datetime;
    @JsonProperty("public")
    private boolean public_event;

    public Tournament() {
    }

    public String getRegistration_enabled() {
        return registration_enabled;
    }

    public void setRegistration_enabled(String registration_enabled) {
        this.registration_enabled = registration_enabled;
    }

    public Date getRegistration_opening_datetime() {
        return registration_opening_datetime;
    }

    public void setRegistration_opening_datetime(Date registration_opening_datetime) {
        this.registration_opening_datetime = registration_opening_datetime;
    }

    public Date getRegistration_closing_datetime() {
        return registration_closing_datetime;
    }

    public void setRegistration_closing_datetime(Date registration_closing_datetime) {
        this.registration_closing_datetime = registration_closing_datetime;
    }

    public boolean isPublic_event() {
        return public_event;
    }

    public void setPublic_event(boolean public_event) {
        this.public_event = public_event;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public Date getScheduled_date_end() {
        return scheduled_date_end;
    }

    @JsonSetter("date_end") //v1 api setter
    public void setDate_end(Date scheduled_date_end) {
        this.scheduled_date_end = scheduled_date_end;
    }

    @JsonSetter("scheduled_date_end") //v2 api setter
    private void setScheduled_date_end(Date scheduled_date_end){
        if(this.scheduled_date_end == null){
            setDate_end(scheduled_date_end);
        }
    }

    public Date getScheduled_date_start() {
        return scheduled_date_start;
    }
    @JsonSetter("date_start") //v1 api setter
    public void setDate_start(Date scheduled_date_start) {
        this.scheduled_date_start = scheduled_date_start;
    }

    @JsonSetter("scheduled_date_start") //v2 api setter
    private void setScheduled_date_start(Date scheduled_date_start){
        if(this.scheduled_date_start == null){
            setDate_start(scheduled_date_start);
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscipline() {
        return this.discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public TournamentStatus getStatus() {
        return this.status;
    }

    public void setStatus(TournamentStatus tournamentStatus) {
        this.status = tournamentStatus;
    }

    public Boolean getOnline() {
        return this.online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getArchived() {
        return this.archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
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
