package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.enums.MatchSort;

import ch.wisv.toornament.model.enums.MatchStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class MatchQuery {

    private boolean hasResult = false;
   @Singular
   private List<Integer> stageIds;
   @Singular
   private List<Integer> groupIds;
   @Singular
   private List<Integer> roundIds;
   @Builder.Default private MatchSort sort = MatchSort.STRUCTURE;
   @Singular private List<Integer> participantIds;
   private boolean scheduled;
    private LocalDateTime scheduledBefore, scheduledAfter;
   @Singular
   private List<MatchStatus> statuses;
   private String customUserIdentifier;
}
