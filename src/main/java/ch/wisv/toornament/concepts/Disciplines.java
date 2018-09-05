package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Discipline;
import ch.wisv.toornament.model.DisciplineDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;

public class Disciplines extends Concept {

    private String endpoint = "/disciplines/";

    public Disciplines(ToornamentClient client) {
        super(client);
    }

    public List<Discipline> getDisciplines(String header) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("disciplines");

        Request request = client.getRequestBuilder()
            .get()

            .url(urlBuilder.build())
            .addHeader("range",header)

            .build();
        String responseBody = null;
        try {
            responseBody = client.executeRequest(request).body().string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Discipline.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve disciplines");
        }
    }

    public DisciplineDetails getDiscipline(String id) {

            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("viewer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("disciplines")
                .addEncodedPathSegment(id);
            Request request = client.getRequestBuilder()
                .get()
                .url(urlBuilder.build())
                .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(DisciplineDetails.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't retrieve discipline with id: " + id);
        }
    }
}
