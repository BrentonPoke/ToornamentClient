package com.toornament.model.header;

public class ReportsHeader  extends RangeHeader{

    @Override
    public ReportsHeader build(Integer start, Integer end) {
        value = "reports="+start+"-"+end;
        return this;
    }
}
