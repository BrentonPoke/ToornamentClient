package ch.wisv.toornament.model.enums;

public enum RegistrationType {
    TEAM("team"), PLAYER("player");
    private String type;

    RegistrationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
