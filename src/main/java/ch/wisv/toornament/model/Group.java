package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Group {
    private String id, stage_id, name;
    private Integer number;
    private boolean closed;
    private Settings settings;

    @Setter
    @Getter
    private class Settings{
        String size;
        //The model for this entity is ridiculous and I hope they change it.
        Object pairing_values;
        public Settings(){}

    }
}
