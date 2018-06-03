package ch.wisv.toornament.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
    private String id;
    private String stage_id;
    private String group_id;
    private Integer number;
    private String name;
    private boolean closed;
    private Object settings;
}
