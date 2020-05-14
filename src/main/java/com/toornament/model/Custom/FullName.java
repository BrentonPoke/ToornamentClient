package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class FullName{

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("first_name")
	private String firstName;
}
