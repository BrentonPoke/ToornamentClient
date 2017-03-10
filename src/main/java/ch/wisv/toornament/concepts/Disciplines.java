package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Discipline;
import ch.wisv.toornament.model.DisciplineDetails;

import java.util.Collections;
import java.util.List;

public class Disciplines extends Concept {

    private String endpoint = "/disciplines/";

    public Disciplines(ToornamentClient client) {
        super(client);
    }

    public List<Discipline> getDisciplines() {
        // TODO: PLACEHOLDER
        return Collections.emptyList();
    }

    public DisciplineDetails getDiscipline(String id) {
        // TODO: PLACEHOLDER
        return new DisciplineDetails();
    }
}
