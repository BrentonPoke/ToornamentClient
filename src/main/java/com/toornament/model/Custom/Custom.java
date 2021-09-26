package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//This will double as a query object as well as a response. That's partly why it's in its own package
@Data
@Builder
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
    public Boolean required;
    @JsonProperty("public")
    public Boolean _public;
    @JsonProperty("position")
    public Integer position;
    public String id;
    public String toString() {
        try {
            return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
