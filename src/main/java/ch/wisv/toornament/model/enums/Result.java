package ch.wisv.toornament.model.enums;

public enum Result {
    UNKNOWN("unknown"),
    WIN("win"),
    DRAW("draw"),
    LOSS("loss");
    private String name;
    public String getName(){return name;}
    Result(String name){
        this.name = name;
    }

}
