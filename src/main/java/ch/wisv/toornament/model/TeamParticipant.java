package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Participant;

import java.util.ArrayList;

public class TeamParticipant extends Participant {
    private ArrayList<Participant> lineup;

    public ArrayList<Participant> getLineup() {
        return lineup;
    }

    public void setLineup(ArrayList<Participant> lineup) {
        this.lineup = lineup;
    }
}
