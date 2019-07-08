package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.Custom.CustomFields;
import ch.wisv.toornament.model.Participant;
import ch.wisv.toornament.model.enums.RegistrationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class RegistrationQuery {
    private String name;
    private RegistrationType type;
    private String tournament_id;
    @JsonProperty("custom_fields")
    CustomFields customFields;
    @Singular("lineup")
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
