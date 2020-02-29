package com.toornament.model.request;

import com.toornament.model.enums.MatchSort;

import com.toornament.model.enums.MatchStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class MatchQuery {

    private boolean hasResult = false;
   @Singular
   private List<Long> stageIds;
   @Singular
   private List<Long> groupIds;
   @Singular
   private List<Long> roundIds;
   @Builder.Default private MatchSort sort = MatchSort.STRUCTURE;
   @Singular private List<Long> participantIds;
   private boolean scheduled;
   @JsonProperty("scheduled_before")
   private LocalDateTime scheduledBefore;
   @JsonProperty("scheduled_after")
   private LocalDateTime scheduledAfter;
   @Singular
   private List<MatchStatus> statuses;
   private String customUserIdentifier;
}
