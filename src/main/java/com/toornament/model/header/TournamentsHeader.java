package com.toornament.model.header;

public class TournamentsHeader extends RangeHeader{

    @Override
    public TournamentsHeader build(Integer start, Integer end) {
        value = "tournaments="+start+"-"+end;
        return this;
    }
}
