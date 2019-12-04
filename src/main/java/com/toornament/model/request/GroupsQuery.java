package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class GroupsQuery {
    @Singular
    @JsonProperty("stage_ids")
    List<Long> stageIds;
    @Singular
    @JsonProperty("stage_numbers")
    List<Integer> stageNumbers;
}
