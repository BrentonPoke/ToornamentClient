package ch.wisv.toornament.concepts;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.wisv.toornament.ToornamentClient;

abstract class Concept {
    ToornamentClient client;
    ObjectMapper mapper;

    public Concept(ToornamentClient client) {
        this.client = client;
        mapper = new ObjectMapper();
    }
}
