package com.toornament.concepts;

import com.fasterxml.jackson.databind.MapperFeature;
import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Custom.Custom;
import com.toornament.model.Stream;
import com.toornament.model.Tournament;
import com.toornament.model.TournamentDetails;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.TournamentQuery;
import java.time.format.DateTimeFormatter;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

public class Tournaments extends Concept {

    public Tournaments(ToornamentClient client) {
        super(client);
    }

    private List<Tournament> requestHelper(Request request){
        try {
            String responseBody = client.executeRequest(request).body().string();
      return mapper
          .readValue(
              responseBody,
              mapper.getTypeFactory().constructCollectionType(List.class, Tournament.class));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Couldn't retrieve tournaments");
        }
    }

    public List<Tournament> getFeaturedTournaments(TournamentQuery parameters, Map<String,String> header) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment("featured");

        if(!parameters.getDisciplines().isEmpty())
            url.addQueryParameter("disciplines", StringUtils.join(parameters.getDisciplines(),","));
        if(!parameters.getStatuses().isEmpty())
            url.addQueryParameter("statuses",StringUtils.join(parameters.getStatuses(),","));
        if(!parameters.getCountries().isEmpty())
            url.addQueryParameter("name",StringUtils.join(parameters.getCountries(),","));
        if(!parameters.getPlatforms().isEmpty())
            url.addQueryParameter("platforms",StringUtils.join(parameters.getPlatforms(),","));
        if(parameters.getIsOnline() != null)
        url.addQueryParameter("is_online",parameters.getIsOnline() ? "1":"0");
        if(parameters.getScheduledAfter() != null)
        url.addQueryParameter("scheduled_after",parameters.getScheduledAfter().format(DateTimeFormatter.ISO_LOCAL_DATE));
        if(parameters.getScheduledBefore() != null)
        url.addQueryParameter("scheduled_before",parameters.getScheduledBefore().format(DateTimeFormatter.ISO_LOCAL_DATE));
        if(!parameters.getSort().toString().isEmpty())
        url.addQueryParameter("sort",parameters.getSort().getName());

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",header.get("range"))
            .build();
        return requestHelper(request);

    }

    public TournamentDetails getTournament(String id) {
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(id);
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, TournamentDetails.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Couldn't get tournament with id: " + id);
        }
    }

    public List<Stream> getStreams(String id, Map<String,String> range){
        HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addPathSegment(id)
            .addEncodedPathSegment("streams");
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",range.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
                Stream.class));
        } catch (IOException | NullPointerException e) {
            throw new ToornamentException("Couldn't retrieve streams from tournament with id "+ id);
        }

    }

    public List<Custom> getViewerCustomFields(String tournamentID){
        return getCustomFields(tournamentID,"viewer");
    }

    public List<Custom> getParticipantCustomFields(String tournamentID){
        return getCustomFields(tournamentID,"participant");
    }

    public List<Custom> getOrganizerCustomFields(String tournamentID){
        return getCustomFields(tournamentID,"organizer");
    }

  private List<Custom> getCustomFields(String tournamentID, String scope) {
    HttpUrl.Builder url = new HttpUrl.Builder();
    url.scheme("https")
        .host("api.toornament.com")
        .addEncodedPathSegment(scope)
        .addEncodedPathSegment("v2")
        .addEncodedPathSegment("tournaments")
        .addEncodedPathSegment(tournamentID);

    Request request = null;
    if(scope.matches("organizer"))
        request = client.getAuthenticatedRequestBuilder().get().url(url.build()).build();
    else
    request = client.getRequestBuilder().get().url(url.build()).build();

    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructCollectionType(List.class, Custom.class));
    } catch (IOException e) {
        logger.error(e.getMessage());
      throw new ToornamentException("Couldn't retrieve custom fields");
    }
        }

        public Custom createCustomField(String tournamentID, Custom query){
            HttpUrl.Builder url = new HttpUrl.Builder();
    if (client.getScope().contains(Scope.ORGANIZER_ADMIN)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("custom-fields");
            }
            RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
           Request request = client.getAuthenticatedRequestBuilder()
           .post(body)
           .url(url.build())
           .build();

            try {
                String responseBody = client.executeRequest(request).body().string();
                return mapper.readValue(responseBody,
                    mapper.getTypeFactory().constructType(Custom.class));
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage());
                throw new ToornamentException("Got IOException creating custom field");
            }

        }

        public Custom getCustomField(String tournamentID, String customFieldID){
            HttpUrl.Builder url = new HttpUrl.Builder();
            if (client.getScope().contains(Scope.ORGANIZER_ADMIN)) {
                url.scheme("https")
                    .host("api.toornament.com")
                    .addEncodedPathSegment("organizer")
                    .addEncodedPathSegment("v2")
                    .addEncodedPathSegment("tournaments")
                    .addEncodedPathSegment(tournamentID)
                    .addEncodedPathSegment("custom-fields")
                    .addEncodedPathSegment(customFieldID);
            }

            Request request = client.getAuthenticatedRequestBuilder()
                .get()
                .url(url.build())
                .build();

            try {
                String responseBody = client.executeRequest(request).body().string();
                return mapper.readValue(responseBody,
                    mapper.getTypeFactory().constructType(Custom.class));
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage());
                throw new ToornamentException("Got IOException creating custom field");
            }
        }

    public Custom updateCustomField(String tournamentID, String customFieldID, Custom query){
        HttpUrl.Builder url = new HttpUrl.Builder();
        if (client.getScope().contains(Scope.ORGANIZER_ADMIN)) {
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("custom-fields")
                .addEncodedPathSegment(customFieldID);
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getAuthenticatedRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(Custom.class));
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ToornamentException("Got IOException updating custom field");
        }
    }

    public Integer deleteCustomField(String tournamentID, String customFieldID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_ADMIN)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions")
                .addEncodedPathSegment(customFieldID);
        }
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }

}
