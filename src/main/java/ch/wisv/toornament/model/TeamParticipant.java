package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
@Getter
@Setter
@EqualsAndHashCode
public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;

}
