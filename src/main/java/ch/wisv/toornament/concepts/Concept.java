package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

abstract class Concept {
    ToornamentClient client;
    ObjectMapper mapper;

    public Concept(ToornamentClient client) {
        this.client = client;
        mapper = new ObjectMapper();
    }
}
