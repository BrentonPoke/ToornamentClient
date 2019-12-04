package com.toornament.model.request;

import com.toornament.model.enums.Platforms;
import com.toornament.model.enums.ScheduledSort;
import com.toornament.model.enums.TournamentStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Singular;

@Builder
@Getter
public class TournamentQuery {
    @Singular
    List<String> disciplines;
    @Singular
    List<TournamentStatus> statuses;
    Boolean featured;
    @JsonProperty("is_online")
    Boolean isOnline;
    @Singular
    List<Locale> countries;
    @Singular
    List<Platforms> platforms;
    @JsonProperty("scheduled_before")
    LocalDate scheduledBefore;
    @JsonProperty("scheduled_after")
    LocalDate scheduledAfter;
    @Default
    ScheduledSort sort = ScheduledSort.SCHEDULED_ASC;
    String name;

}
