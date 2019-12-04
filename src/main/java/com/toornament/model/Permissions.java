package com.toornament.model;

import com.toornament.model.enums.Attribute;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Permissions {
    private List<Attribute> attributes;
    private String email;
    private String id;

}
