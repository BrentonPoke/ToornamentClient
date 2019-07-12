package ch.wisv.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class GroupsQuery {
    @Singular
    @JsonAlias("stage_ids")
    List<Long> stageIds;
    @Singular
    @JsonAlias("stage_numbers")
    List<Integer> stageNumbers;
}
