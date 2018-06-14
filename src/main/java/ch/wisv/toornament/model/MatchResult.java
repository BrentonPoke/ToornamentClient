package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class MatchResult {

    private MatchStatus status;
    private List<Opponent> opponents;

}
