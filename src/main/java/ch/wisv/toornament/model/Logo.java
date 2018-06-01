package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logo {

    @JsonAlias({"logo_small","icon_small"})
    private String logo_small;
    @JsonAlias({"logo_medium","icon_medium"})
    private String logo_medium;
    @JsonAlias({"logo_large","icon_large"})
    private String logo_large;
    private String original;

}
