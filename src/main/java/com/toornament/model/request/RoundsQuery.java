package com.toornament.model.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class RoundsQuery {
    @Singular
    private List<Long> stageIds;
    @Singular
    private List<Long> groupIds;
    @Singular
    List<Integer> stageNumbers;
    @Singular
    List<Integer> groupNumbers;

}
