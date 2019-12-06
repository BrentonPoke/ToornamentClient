package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Custom {

    @JsonProperty("machine_name")
    private String machineName;
    @JsonProperty("label")
    public String label;
    @JsonProperty("target_type")
    public String targetType;
    @JsonProperty("type")
    public String type;
    @JsonProperty("default_value")
    public String defaultValue;
    @JsonProperty("required")
    public String required;
    @JsonProperty("public")
    public String _public;
    @JsonProperty("position")
    public Integer position;
    public String id;

}
