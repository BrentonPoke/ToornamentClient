package ch.wisv.toornament.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
@Getter
@Setter
@EqualsAndHashCode
public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;

}
