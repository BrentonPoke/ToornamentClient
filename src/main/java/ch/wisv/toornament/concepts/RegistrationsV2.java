package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.model.enums.Scope;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class RegistrationsV2 extends Concept {
    private String tournamentID;
    public RegistrationsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }

    private Request getRegistrations(Map<String, String> header, Map<String, String> paramsMap) {
        HttpUrl.Builder url = new HttpUrl.Builder();
    if (client.getScope().contains(Scope.ORGANIZER_REGISTRATION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("registrations");
      for (Map.Entry<String, String> params : paramsMap.entrySet()) {
        url.addQueryParameter(params.getKey(), params.getValue());
      }
        }
        return client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", header.get("range"))
            .build();
    }

}
