package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Participant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;

}
