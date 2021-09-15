package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Data;

public @Data class Bracket{
    @JsonProperty("stage_id")
	private String stageId;
	private List<Opponent> opponents;
	private String type;
	private String branch;
    @JsonProperty("tournament_id")
	private String tournamentId;
    @JsonProperty("round_id")
	private String roundId;
	private Integer number;
    @JsonProperty("scheduled_datetime")
	private OffsetDateTime scheduledDatetime;
	private Integer depth;
    @JsonProperty("group_id")
	private String groupId;
	private String id;
    @JsonProperty("played_at")
	private OffsetDateTime playedAt;
	private String status;
}
