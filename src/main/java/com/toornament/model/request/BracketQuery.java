package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Singular;
@Builder
public class BracketQuery {
    @Singular
    @JsonProperty("stage_ids")
    List<Long> stageIds;
    @Singular
    @JsonProperty("tournament_ids")
    List<Long> tournamentIDs;
    @Singular
    @JsonProperty("stage_numbers")
    List<Integer> stageNumbers;
    @Singular
    @JsonProperty("group_numbers")
    List<Integer> groupNumbers;
    @Singular
    @JsonProperty("round_numbers")
    List<Integer> roundNumbers;
    @JsonAlias("group_ids")
    @Singular
    List<String> groupIDs;
    @JsonAlias("round_ids")
    @Singular
    private List<Long> roundIds;
    @JsonProperty("min_depth")
    private Integer minDepth;
    @JsonProperty("max_depth")
    private Integer maxDepth;
}
