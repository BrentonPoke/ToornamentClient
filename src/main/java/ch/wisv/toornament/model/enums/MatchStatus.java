package ch.wisv.toornament.model.enums;

public enum MatchStatus {

    PENDING("pending"),
    RUNNING("running"),
    COMPLETED("completed");

    private String name;
    public String getName(){return name;}

    MatchStatus(String name){ this.name = name; }
}
