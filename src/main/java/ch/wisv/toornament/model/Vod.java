package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.VodCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Vod extends Video {

    private VodCategory category;
    private BigInteger match_id;

    public BigInteger getMatch_id() {
        return match_id;
    }
}
