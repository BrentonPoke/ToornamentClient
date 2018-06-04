package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Round;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RoundsV2 extends Concept {
    private String id;
    public RoundsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.id = tournamentID;
    }

    public List<Round> getRounds(Map<String,String> paramsMap, Map<String,String> header) {

        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id)
            .addEncodedPathSegment("rounds");
        for (Map.Entry<String, String> params : paramsMap.entrySet()) {
            url.addQueryParameter(params.getKey(), params.getValue());
        }
        Request request = client.getRequestBuilder()
            .get()
            .url(url.toString())
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
}
