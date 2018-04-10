package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Tournament {

    private String id;
    private String discipline;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private Status status;
    private Date scheduled_date_start;
    private Date scheduled_date_end;
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

    public Tournament() {
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

    public void setScheduled_date_end(Date scheduled_date_end) {
        this.scheduled_date_end = scheduled_date_end;
    }

    public Date getScheduled_date_start() {
        return scheduled_date_start;
    }

    public void setScheduled_date_start(Date scheduled_date_start) {
        this.scheduled_date_start = scheduled_date_start;
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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

    public String toString() {
        return "ch.wisv.toornament.model.Tournament(id=" + this.getId() + ", discipline=" + this
            .getDiscipline() + ", name=" + this.getName() + ", fullName=" + this.getFullName()
            + ", status=" + this.getStatus() + ", dateStart=" + this.getDateStart() + ", dateEnd="
            + this.getDateEnd() + ", online=" + this.getOnline() + ", isPublic=" + this
            .getIsPublic()
            + ", archived=" + this.getArchived() + ", location=" + this.getLocation() + ", country="
            + this.getCountry() + ", size=" + this.getSize() + ")";
    }
}
