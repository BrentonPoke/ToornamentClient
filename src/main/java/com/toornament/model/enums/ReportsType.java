package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportsType {
    DISPUTE("dispute"), REPORT("report");
    private String name;
    @JsonValue
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

    ReportsType(String name) {
        this.name = name;
    }
}
