package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RankingItem{

	@JsonProperty("number")
	private Integer number;

	@JsonProperty("group_id")
	private String groupId;

	@JsonProperty("rank")
	private Integer rank;

	@JsonProperty("id")
	private String id;

	@JsonProperty("position")
	private Integer position;

	@JsonProperty("participant")
	private Participant participant;

	@JsonProperty("properties")
	private Properties properties;

	@JsonProperty("points")
	private Integer points;
}