package ch.wisv.toornament.model.enums;

public enum ScheduledSort {
    SCHEDULED_ASC("scheduled_asc"), SCHEDULED_DESC(" scheduled_desc");

    private String name;

    public String getName() {
        return name;
    }

    ScheduledSort(String name){
        this.name = name;
    }
}
