package ch.wisv.toornament.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Group {
    private String id, stage_id, name;
    private Integer number;
    private boolean closed;
}
