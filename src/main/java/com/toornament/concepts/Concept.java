package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

abstract class Concept {
    ToornamentClient client;
    ObjectMapper mapper;
    Logger logger;

    public Concept(ToornamentClient client) {
        this.client = client;
        mapper = client.getMapper();
    }
}
