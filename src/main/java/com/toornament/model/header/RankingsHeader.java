package com.toornament.model.header;

public class RankingsHeader extends RangeHeader{

    @Override
    RangeHeader build(Integer start, Integer end) {
        value = "rankings="+start+"-"+end;
        return this;
    }
}
