package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.MatchStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MatchResult {

    private MatchStatus status;
    private List<Opponent> opponents;

}
