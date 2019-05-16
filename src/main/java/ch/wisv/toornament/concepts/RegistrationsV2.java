package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;

public class RegistrationsV2 extends Concept {
    private String tournamentID;
    public RegistrationsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }


}
