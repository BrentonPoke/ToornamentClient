package com.toornament.model.enums;

public enum Sort {
    ASCENDING("created_asc"),
    DESCENDING("created_desc"),
    ALPHABETIC("alphabetic");
    private String sort;

    Sort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return sort;
    }
}
