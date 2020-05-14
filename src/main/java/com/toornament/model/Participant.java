
package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Custom.CustomFields;
import com.toornament.model.enums.ParticipantType;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@SuppressWarnings("unused")
public class Participant {

    @JsonProperty("custom_fields")
    private CustomFields customFields;
    private String id;
    @JsonProperty("user_id")
    private String userID;
    private String name;
    private String email;
    @JsonProperty("custom_user_identifier")
    private String customUserIdentifier;
    private List<Participant> lineup;
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
