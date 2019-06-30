package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Round;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.request.RoundsQuery;
import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@Getter
public class RoundsV2 extends Concept {
    private String tournamentID;
    public RoundsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }

    public List<Round> getRounds(RoundsQuery parameter, Map<String,String> header) {

        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("rounds");

        url.addQueryParameter("stage_ids", StringUtils.join(parameter.getStageIds(), ","));
        url.addQueryParameter("group_ids", StringUtils.join(parameter.getGroupIds(), ","));
        url.addQueryParameter("group_numbers", StringUtils.join(parameter.getGroupNumbers(), ","));
        url.addQueryParameter("stage_numbers", StringUtils.join(parameter.getStageNumbers(), ","));

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",header.get("range"))
            .build();
        try {
        String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Round.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        throw new ToornamentException("Got IOExcption getting Rounds");
    }
    }

    public Round getRoundByID(String roundID){
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("rounds")
            .addEncodedPathSegment(roundID);

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, Round.class);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ToornamentException("Couldn't get Round with id: " + roundID);
        }
    }
}
