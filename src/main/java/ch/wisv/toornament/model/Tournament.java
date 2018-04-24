package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.TournamentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
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
