package com.toornament.model.header;

public class StandingsHeader extends RangeHeader{

    @Override
    public StandingsHeader build(Integer start, Integer end) {
        value = "items="+start+"-"+end;
        return this;
    }
}
