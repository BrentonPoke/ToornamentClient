package com.toornament.model.header;

public class GroupsHeader extends RangeHeader{

    @Override
    public GroupsHeader build(Integer start, Integer end) {
        value = "groups="+start.toString()+"-"+end.toString();
        return this;
    }
}
