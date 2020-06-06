package com.toornament.model.Custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Address {
    private String address;
    @JsonProperty("postal_code")
    private String postalCode;
    private String city;
    private String country;

}
