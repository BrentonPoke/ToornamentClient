package com.toornament.model.Custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Address {
    private String address;
    private String postal_code;
    private String city;
    private String country;

}
