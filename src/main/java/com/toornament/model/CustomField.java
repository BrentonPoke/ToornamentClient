package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomField {
    @JsonAlias("machine_name")
    String machineName;
    @JsonAlias("target_type")
    String targetType;
    @JsonAlias("default_value")
    String defaultValue;
    Boolean required;
    @JsonAlias("public")
    Boolean isPublic;
    Integer position;
    String id;
}
