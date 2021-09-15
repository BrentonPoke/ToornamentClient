package com.toornament.concepts;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Bracket;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.BracketHeader;
import com.toornament.model.request.BracketQuery;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Brackets extends Concept {
public Brackets(ToornamentClient client) {
        super(client);
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Bracket> getBracketNodes(BracketHeader header, BracketQuery query){
    this.checkScope(Scope.ORGANIZER_RESULT);
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("organizer")
            .addPathSegment("v2")
            .addPathSegment("bracket-nodes")
            .addQueryParameter("stage_ids", StringUtils.join(query.getStageIds(), ","))
            .addQueryParameter("stage_numbers", StringUtils.join(query.getStageNumbers(), ","))
            .addQueryParameter("tournament_ids", StringUtils.join(query.getTournamentIDs(), ","))
            .addQueryParameter("round_ids", StringUtils.join(query.getRoundIds(), ","))
            .addQueryParameter("round_numbers", StringUtils.join(query.getRoundNumbers(), ","))
            .addQueryParameter("group_ids", StringUtils.join(query.getGroupIDs(), ","))
            .addQueryParameter("group_numbers", StringUtils.join(query.getGroupNumbers(), ","))
            .addQueryParameter("min_depth", StringUtils.join(query.getMinDepth(), ","))
            .addQueryParameter("max_depth", StringUtils.join(query.getMaxDepth(), ","));

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
                Bracket.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Couldn't retrieve disciplines");
        }
    }
}
