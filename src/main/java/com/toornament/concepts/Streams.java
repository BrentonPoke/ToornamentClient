package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Custom.Custom;
import com.toornament.model.Stream;
import com.toornament.model.Tournament;
import com.toornament.model.Video;
import com.toornament.model.enums.Scope;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Streams extends Concept{
    private String tournamentID;
    public Streams(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }
    public Stream getStreams(Map<String,String> range){
        Builder urlBuilder = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("streams");
        }
        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range",range.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(
                List.class,
                Stream.class));
        } catch (IOException e) {
            e.getMessage();
            throw new ToornamentException("Couldn't retrieve streams");
        }
    }

    public Stream createStream(Video query){
        Builder urlBuilder = new Builder();
        logger.debug("Scopes: {}",client.getScope().toString());
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            urlBuilder
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("streams");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
        .post(body)
        .url(urlBuilder.build())
        .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Stream.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException creating stream");
        }
    }

    public Stream getStream(String streamID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("streams")
                .addEncodedPathSegment(streamID);
        }

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Stream.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting stream");
        }
    }

    public Stream updateStream(String streamID, Video query){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("streams")
                .addEncodedPathSegment(streamID);
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Stream.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException creating stream");
        }
    }

    public Integer deleteStream(String streamID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("streams")
                .addEncodedPathSegment(streamID);
        }
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();

        return client.executeRequest(request).code();
    }

    public List<String> getStreamsOfMatch(String matchID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("streams");
        }

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class,String.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting match streams");
        }
    }

    public List<Stream> updateMatchStreams(String matchID, List<String> streamList){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment(matchID)
                .addEncodedPathSegment("streams");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),streamList.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .put(body)
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class,String.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException updating match streams");
        }
    }
}
