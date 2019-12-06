package com.toornament.model.enums;

public enum CustomFieldTargetType {
    TEAM("team"), PLAYER("player"), TEAM_PLAYER("team_player");
    private String name;

    public String getName() {
        return name;
    }

    CustomFieldTargetType(String name) {
        this.name = name;
    }
}
