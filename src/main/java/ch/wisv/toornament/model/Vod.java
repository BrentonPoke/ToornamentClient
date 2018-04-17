package ch.wisv.toornament.model;

import ch.wisv.toornament.model.enums.VodCategory;

import java.math.BigInteger;

public class Vod extends Video {

    private VodCategory category;
    private BigInteger match_id;

    public BigInteger getMatch_id() {
        return match_id;
    }

    public void setMatch_id(BigInteger match_id) {
        this.match_id = match_id;
    }

    public VodCategory getCategory() {
        return category;
    }

    public void setCategory(VodCategory category) {
        this.category = category;
    }
}
