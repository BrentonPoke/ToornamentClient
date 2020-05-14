package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Custom.CustomFields;
import java.util.ArrayList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Registration {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("email")
    private String email;

    @JsonProperty("tournament_id")
    private String tournamentId;

    @JsonProperty("status")
    private String status;
    @JsonProperty("custom_user_identifier")
    private String customUserIdentifier;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("custom_fields")
    private CustomFields customFields;

    @JsonProperty("participant_id")
    private String participantId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;
    private ArrayList<Participant> lineup;

    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
