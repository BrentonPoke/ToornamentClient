package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Participant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;

}
