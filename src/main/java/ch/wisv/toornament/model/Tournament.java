package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Tournament {

    String id;
    String discipline;
    String name;
    @JsonProperty("full_name")
    String fullName;
    Status status;
    @JsonProperty("date_start")
    Date dateStart;
    @JsonProperty("date_end")
    Date dateEnd;
    Boolean online;
    @JsonProperty("public")
    Boolean isPublic;
    Boolean archived;
    String location;
    String country;
    Integer size;
}
