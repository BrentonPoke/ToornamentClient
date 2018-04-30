package ch.wisv.toornament.model;

import ch.wisv.toornament.model.Custom.CustomFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Opponent {
    private int number;
    private Participant participant;
    private Object result;
    private Integer rank;
    private Integer score;
    private boolean forfeit;
    private CustomFields custom_fields;


    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
