package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebhookSubscription {
    @JsonProperty("event_name")
    String eventName;
    @JsonProperty("scope_id")
    String scopeID;
    String id;
    String scope;

}
