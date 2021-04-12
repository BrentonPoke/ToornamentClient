package com.toornament.model.header;

public class ParticipantsHeader extends RangeHeader{

    @Override
    public ParticipantsHeader build(Integer start, Integer end) {
        value = "participants="+start+"-"+end;
        return this;
    }
}
