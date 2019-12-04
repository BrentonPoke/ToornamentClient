package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class Concept {
    ToornamentClient client;
    ObjectMapper mapper;

    public Concept(ToornamentClient client) {
        this.client = client;
        mapper = client.getMapper();
    }
}
