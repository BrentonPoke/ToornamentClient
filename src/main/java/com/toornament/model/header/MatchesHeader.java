package com.toornament.model.header;

public class MatchesHeader extends RangeHeader{

    @Override
    public MatchesHeader build(Integer start, Integer end) {
        value = "matches="+start.toString()+"-"+end.toString();
        return this;
    }

}
