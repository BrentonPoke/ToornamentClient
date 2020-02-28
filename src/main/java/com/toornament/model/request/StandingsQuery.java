package com.toornament.model.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class StandingsQuery {
    @Singular
    private List<String> tournamentIds;
    @Singular
    private List<String> participantIds;
}
