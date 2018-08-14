package ch.wisv.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResultV2 {
    @JsonProperty("unknown") UNKNOWN ,
    @JsonProperty("win") WIN,
    @JsonProperty("loss") LOSS,
    @JsonProperty("draw") DRAW

}
