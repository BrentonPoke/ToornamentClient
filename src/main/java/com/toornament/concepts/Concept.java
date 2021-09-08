package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.exception.ToornamentException;
import com.toornament.model.enums.Scope;
import org.slf4j.Logger;

abstract class Concept {
    ToornamentClient client;
    ObjectMapper mapper;
    Logger logger;

    void checkScope(Scope scope){
        if (!this.client.getScope().contains(scope))
        throw new ToornamentException("Wrong scope, expecting "+scope+ " for call");
    }

    public Concept(ToornamentClient client) {
        this.client = client;
        mapper = client.getMapper();
    }
}
