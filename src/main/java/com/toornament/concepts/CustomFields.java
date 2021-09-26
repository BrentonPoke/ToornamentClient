package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Custom.Custom;
import com.toornament.model.enums.Scope;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CustomFields extends Concept{

    public CustomFields(ToornamentClient client) {
        super(client);
    }

    public List<Custom> getCustomFields() {
        checkScope(Scope.ORGANIZER_ADMIN);
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addPathSegment("organizer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment("custom-fields");

        Request request = client.getAuthenticatedRequestBuilder().get().url(url.build()).build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(
                responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, Custom.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Couldn't retrieve custom fields");
        }
    }

    public Custom createCustomField(Custom query){
        checkScope(Scope.ORGANIZER_ADMIN);

        HttpUrl.Builder url = new HttpUrl.Builder();

            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment("custom-fields");

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .post(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Custom.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException creating custom field");
        }

    }

    public Custom getCustomField(String customFieldID){
        checkScope(Scope.ORGANIZER_ADMIN);
        HttpUrl.Builder url = new HttpUrl.Builder();

            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("custom-fields")
                .addEncodedPathSegment(customFieldID);

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Custom.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException creating custom field");
        }
    }
    //Not all fields in the Custom object should be set for this. Only label, default_value, required, public and position
    public Custom updateCustomField(String customFieldID, Custom query){
        checkScope(Scope.ORGANIZER_ADMIN);
        HttpUrl.Builder url = new HttpUrl.Builder();
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("custom-fields")
                .addEncodedPathSegment(customFieldID);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try {
            String responseBody = Objects.requireNonNull(client.executeRequest(request).body()).string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Custom.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException updating custom field");
        }
    }

    public Integer deleteCustomField(String customFieldID){
        checkScope(Scope.ORGANIZER_ADMIN);
        HttpUrl.Builder url = new HttpUrl.Builder();

            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("permissions")
                .addEncodedPathSegment(customFieldID);

        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }

}
