package com.toornament.model.header;

public class StandingsHeader extends RangeHeader{

    @Override
    public StandingsHeader build(Integer start, Integer end) {
        value = "standings="+start+"-"+end;
        return this;
    }
}
