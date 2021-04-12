package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Discipline;
import com.toornament.model.DisciplineDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.toornament.model.header.DisciplinesHeader;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import org.slf4j.LoggerFactory;

public class Disciplines extends Concept {

    public Disciplines(ToornamentClient client) {
        super(client);
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Discipline> getDisciplines(DisciplinesHeader header) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("disciplines");

        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range",header.get())

            .build();
        String responseBody = null;
        try {
            responseBody = client.executeRequest(request).body().string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Discipline.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            throw new ToornamentException("Couldn't retrieve discipline with id: " + id);
        }
    }
}
