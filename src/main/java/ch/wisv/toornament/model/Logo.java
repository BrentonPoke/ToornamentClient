package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Logo {

    @JsonAlias({"logo_small","icon_small"})
    private String logo_small;
    @JsonAlias({"logo_medium","icon_medium"})
    private String logo_medium;
    @JsonAlias({"logo_large","icon_large"})
    private String logo_large;
    private String original;

}
