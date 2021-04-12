package com.toornament.model.header;

public class RegistrationsHeader  extends RangeHeader{

    @Override
    public RegistrationsHeader build(Integer start, Integer end) {
        value = "registrations="+start+"-"+end;
        return this;
    }
}
