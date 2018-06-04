package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {
    private int number;
    private MatchStatus status;
    private List<Opponent> opponents;
    private String type;
    private String map;

}
