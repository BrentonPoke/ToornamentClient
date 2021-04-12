package com.toornament.model.header;

public class StreamsHeader extends RangeHeader {

    @Override
    StreamsHeader build(Integer start, Integer end) {
        value = "streams="+start+"-"+end;
        return this;
    }
}
