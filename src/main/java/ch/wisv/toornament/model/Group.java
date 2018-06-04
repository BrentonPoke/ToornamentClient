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
}
