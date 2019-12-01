package ch.wisv.toornament.model.request;

import ch.wisv.toornament.model.enums.Attribute;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
@Getter
@Builder
public class PermissionsQuery {
    @Singular("attribute")
    private List<Attribute> attributes;
    private String email;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
