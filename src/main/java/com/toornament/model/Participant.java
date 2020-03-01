
package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toornament.model.Custom.CustomFields;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@SuppressWarnings("unused")
public class Participant {

    @JsonProperty("custom_fields")
    private CustomFields customFields;
    private String id;
    private String name;
    @JsonProperty("custom_user_identifier")
    private String customUserIdentifier;

}
