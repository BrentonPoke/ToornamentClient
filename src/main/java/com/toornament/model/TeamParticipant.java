package com.toornament.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;
}
