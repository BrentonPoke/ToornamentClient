package ch.wisv.toornament.concepts;

import java.util.Collections;
import java.util.List;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.Discipline;
import ch.wisv.toornament.model.DisciplineDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
import okhttp3.Request;

public class Disciplines extends Concept {

    private String endpoint = "/disciplines/";

    public Disciplines(ToornamentClient client) {
        super(client);
    }

    public List<Discipline> getDisciplines() throws IOException {

        Request request = client.getAuthenticatedRequestBuilder().get().url("https://api.toornament.com/v1/disciplines/").build();
         String responseBody = client.executeRequest(request).body().string();
         mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Discipline.class));
    }

    public DisciplineDetails getDiscipline(String id) throws IOException {

        Request request = client.getAuthenticatedRequestBuilder().get().url("https://api.toornament.com/v1/disciplines/"+ id ).build();
         String responseBody = client.executeRequest(request).body().string();
         mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(DisciplineDetails.class));
    }
}
