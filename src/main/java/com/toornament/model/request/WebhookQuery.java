package com.toornament.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WebhookQuery {
    Boolean enabled;
    String url, name;
}
