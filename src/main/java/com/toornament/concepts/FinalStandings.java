package com.toornament.concepts;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Standings;
import com.toornament.model.request.StandingsQuery;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class FinalStandings extends Concept {

    public FinalStandings(ToornamentClient client) {
        super(client);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Standings> getFinalStandings(String range, StandingsQuery query){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("standings");

        if(!query.getParticipantIds().isEmpty())
            urlBuilder.addQueryParameter("participant_ids", StringUtils.join(query.getParticipantIds(),","));
        if(!query.getTournamentIds().isEmpty())
            urlBuilder.addQueryParameter("tournament_ids", StringUtils.join(query.getTournamentIds(),","));
        logger.debug("url: {}",urlBuilder.build().toString());
        Request request = client.getRequestBuilder()
            .get()
            .url(urlBuilder.build())
            .addHeader("range",range)
            .build();
        String responseBody = null;
        try {
            responseBody = client.executeRequest(request).body().string();
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Standings.class));
        } catch (IOException e) {
            ToornamentException exception =new ToornamentException("Couldn't retrieve standings");
            exception.setError(e.getMessage());
            throw exception;
        }
    }
}
