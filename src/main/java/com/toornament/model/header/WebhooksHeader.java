package com.toornament.model.header;

public class WebhooksHeader extends RangeHeader {

    @Override
    WebhooksHeader build(Integer start, Integer end) {
        value = "webhooks="+start+"-"+end;
        return this;
    }
}
