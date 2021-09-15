package com.toornament.model.header;

public class BracketHeader extends RangeHeader{
    @Override
 public BracketHeader build(Integer start, Integer end) {
     value = "nodes="+start+"-"+end;
     return this;
 }
}
