package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Stream;
import com.toornament.model.Tournament;
import com.toornament.model.enums.Scope;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;

public class StreamsV2 extends Concept{
    private String tournamentID;
    public StreamsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }
    public Stream getStreams(Map<String,String> range){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
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
            .url(url.build())
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
}
