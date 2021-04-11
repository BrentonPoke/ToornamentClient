package com.toornament.model.header;

public class GroupHeader extends RangeHeader{

    @Override
    public RangeHeader build(Integer start, Integer end) {
        value = "groups="+start.toString()+"-"+end.toString();
        return this;
    }
}
