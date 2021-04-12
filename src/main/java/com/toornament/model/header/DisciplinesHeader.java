package com.toornament.model.header;

public class DisciplinesHeader extends RangeHeader{

    @Override
    public DisciplinesHeader build(Integer start, Integer end) {
        value = "disciplines="+start+"-"+end;
        return this;
    }
}
