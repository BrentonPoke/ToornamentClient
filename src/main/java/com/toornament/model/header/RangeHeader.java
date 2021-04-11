package com.toornament.model.header;
public abstract class RangeHeader {
    String value;
    abstract RangeHeader build(Integer start, Integer end);

    public String get() {
        return value;
    }
}
