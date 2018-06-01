package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.VodCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
@Setter
@Getter
public class Vod extends Video {

    private VodCategory category;
    private BigInteger match_id;

    public BigInteger getMatch_id() {
        return match_id;
    }
}
