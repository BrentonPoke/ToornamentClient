package com.toornament.model.request;

import com.toornament.model.Custom.CustomFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.RegisteredParticipant;
import com.toornament.model.enums.RegistrationType;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@Builder
public class RegistrationQuery {
    public String name;
    public String email;
    public RegistrationType type;
    @JsonProperty("tournament_id")
    public String tournamentID;
    @Singular("lineup")
    public List<RegisteredParticipant> lineup;
    @JsonProperty("custom_fields")
    private CustomFields customFields;
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
