package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VodCategory {

    @JsonProperty("replay")
    REPLAY,
    @JsonProperty("highlight")
    HIGHLIGHT,
    @JsonProperty("bonus")
    BONUS;
}
