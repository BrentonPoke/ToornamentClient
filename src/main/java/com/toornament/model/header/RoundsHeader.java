package com.toornament.model.header;

public class RoundsHeader extends RangeHeader {

    @Override
    public RoundsHeader build(Integer start, Integer end) {
        value = "rounds="+start+"-"+end;
        return this;
    }
}
