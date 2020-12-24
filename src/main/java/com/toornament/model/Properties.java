package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties{

	@JsonProperty("wins")
	private Integer wins;

	@JsonProperty("forfeits")
	private Integer forfeits;

	@JsonProperty("score_against")
	private Integer scoreAgainst;

	@JsonProperty("score_for")
	private Integer scoreFor;

	@JsonProperty("score_difference")
	private Integer scoreDifference;

	@JsonProperty("draws")
	private Integer draws;

	@JsonProperty("losses")
	private Integer losses;

	@JsonProperty("played")
	private Integer played;
}