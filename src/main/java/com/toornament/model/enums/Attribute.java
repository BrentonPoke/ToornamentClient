package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Attribute{
    EDIT("edit"),
    REPORT("report"),
    DELETE("delete"),
    AUTHORIZE("authorize"),
    FILL("fill"),
    REGISTER("register");

    Attribute(String name){this.name = name;}

    private String name;
    @JsonValue
    public String getName() {
        return name;
    }
}
