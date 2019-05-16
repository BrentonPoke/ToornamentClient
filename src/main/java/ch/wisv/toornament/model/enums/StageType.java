package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StageType {
    GROUP("group"),
    LEAGUE("league"),
    SWISS("swiss"),
    SINGLE_ELIMINATION("single_elimination"),
    DOUBLE_ELIMINATION("double_elimination"),
    BRACKET_GROUP("bracket_group");
    @JsonValue
    private String type;
    StageType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
