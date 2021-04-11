package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.MatchDetails;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.MatchStatus;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.MatchesHeader;
import com.toornament.model.request.MatchQuery;
import java.time.format.DateTimeFormatter;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Matches extends Concept {
    private TournamentDetails tournament;
    private static final DateTimeFormatter RFC_3339 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    public Matches(ToornamentClient client, TournamentDetails tournament) {
        super(client);
        this.tournament = tournament;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<MatchDetails> getMatches(MatchQuery parameter, MatchesHeader header) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        logger.debug("Scopes: {}",client.getScope().toString());
        String scope = "viewer";

      if (client.getScope().contains(Scope.ORGANIZER_RESULT))
          scope = "organizer";

        urlBuilder
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment(scope)
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournament.getId())
            .addPathSegment("matches");
        if(!parameter.getStageIds().isEmpty())
        urlBuilder.addQueryParameter("stage_ids", StringUtils.join(parameter.getStageIds(), ","));
        if(!parameter.getGroupIds().isEmpty())
        urlBuilder.addQueryParameter("group_ids", StringUtils.join(parameter.getGroupIds(), ","));
        if(!parameter.getRoundIds().isEmpty())
        urlBuilder.addQueryParameter("round_ids", StringUtils.join(parameter.getRoundIds(), ","));
      if (!parameter.getStatuses().isEmpty()) {
          StringBuilder s = new StringBuilder();
          for (MatchStatus e : parameter.getStatuses() ) {
              s.append(e.getName()).append(',');
          }
        urlBuilder.addQueryParameter("statuses", s.deleteCharAt(s.length()-1).toString());
        }
        if(!parameter.getParticipantIds().isEmpty())
        urlBuilder.addQueryParameter(
            "participant_ids", StringUtils.join(parameter.getParticipantIds(), ","));
        if(parameter.getScheduled() != null)
        urlBuilder.addQueryParameter("is_scheduled", parameter.getScheduled() ? "1" : "0");
        if(parameter.getScheduledBefore() != null)
        urlBuilder.addQueryParameter("scheduled_before", parameter.getScheduledBefore().format(RFC_3339));
        if(parameter.getScheduledAfter() != null)
        urlBuilder.addQueryParameter("scheduled_after", parameter.getScheduledAfter().format(RFC_3339));
        urlBuilder.addEncodedQueryParameter("custom_user_identifier", parameter.getCustomUserIdentifier());
        urlBuilder.addQueryParameter("sort", parameter.getSort().getName());

        logger.debug("url: {}",urlBuilder.build().toString());
        Request.Builder request;
        if(scope.equals("organizer"))
            request = client.getAuthenticatedRequestBuilder();
        else
            request = client.getRequestBuilder();

        request
            .get()
            .url(urlBuilder.build())
            .addHeader("Range",header.get());
      try{
            String responseBody = client.executeRequest(request.build()).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, MatchDetails.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException getting matches");
        }
    }

public MatchDetails updateMatch(MatchDetails details, String matchId){
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
    if(client.getScope().contains(Scope.ORGANIZER_RESULT)){
        urlBuilder
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("organizer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournament.getId())
            .addPathSegment("matches")
            .addPathSegment(matchId);

    }
    logger.debug("url: {}",urlBuilder.build().toString());
    RequestBody body = RequestBody.create(MediaType.parse("Application/Json"),details.toString());
    Request request = client.getRequestBuilder().patch(body).build();
    return matchDetailsHelper(request);
}

    public MatchDetails getMatch(String matchId){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        urlBuilder
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournament.getId())
            .addPathSegment("matches")
            .addPathSegment(matchId);
        Request request = client.getRequestBuilder().get().url(urlBuilder.toString()).build();
        return matchDetailsHelper(request);

    }
    private MatchDetails matchDetailsHelper(Request request) {
        try {

            String responseBody = client.executeRequest(request).body().string();
            return mapper
                .readValue(responseBody, mapper.getTypeFactory().constructType(MatchDetails.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException getting match");
        }
    }
}
