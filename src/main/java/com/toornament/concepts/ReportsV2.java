package com.toornament.concepts;

import com.toornament.ToornamentClient;

public class ReportsV2 extends Concept {
    private String tournamentID;
    public ReportsV2(ToornamentClient client, String tournamentID){
        super(client);
        this.tournamentID = tournamentID;
    }
}
