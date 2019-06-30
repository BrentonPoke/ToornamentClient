package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Match;
import ch.wisv.toornament.model.MatchDetails;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.request.MatchQuery;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

public class MatchesV2 extends Concept {
    private TournamentDetails tournament;
    public MatchesV2(ToornamentClient client, TournamentDetails tournament) {
        super(client);
        this.tournament = tournament;
    }

    public List<Match> getMatches(MatchQuery parameter,String headers) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

      if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
        urlBuilder
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
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
        if(!parameter.getStatuses().isEmpty())
        urlBuilder.addQueryParameter("statuses", StringUtils.join(parameter.getStatuses(), ","));
        urlBuilder.addQueryParameter(
            "participant_ids", StringUtils.join(parameter.getParticipantIds(), ","));
        urlBuilder.addQueryParameter("is_scheduled", parameter.isScheduled() ? "1" : "0");
        urlBuilder.addQueryParameter("scheduled_before", parameter.getScheduledBefore().toString());
        urlBuilder.addQueryParameter("scheduled_after", parameter.getScheduledAfter().toString());
        urlBuilder.addQueryParameter("custom_user_identifier", parameter.getCustomUserIdentifier());
        urlBuilder.addQueryParameter("sort", parameter.getSort().name());
            }

            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url(urlBuilder.build())
                .addHeader("Range",headers)
                .build();
      try{
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Match.class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting matches");
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
            throw new ToornamentException("Got IOExcption getting match");
        }
    }
}
