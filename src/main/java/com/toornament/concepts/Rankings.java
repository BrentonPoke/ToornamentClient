package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.RankingItem;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.RankingsHeader;
import com.toornament.model.request.RankingItemQuery;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Rankings extends Concept {
    private String tournamentID;
  public Rankings(ToornamentClient client, String tournamentID) {
    super(client);
      this.tournamentID = tournamentID;
      logger = LoggerFactory.getLogger(this.getClass());
  }

  List<RankingItem> getRankingItems(RankingItemQuery query, RankingsHeader header, String stageID) {
    Builder urlBuilder = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_RESULT)) {
      urlBuilder
          .scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("stages")
          .addEncodedPathSegment(stageID)
          .addEncodedPathSegment("ranking-items")
      .addQueryParameter("group_ids", StringUtils.join(query.getGroupIDs(),","))
      .addQueryParameter("group_numbers",StringUtils.join(query.getGroupNumbers(),","))
      .addQueryParameter("participant_ids", StringUtils.join(query.getParticipantIDs(),","))
      .addQueryParameter("custom_user_identifiers",StringUtils.join(query.getCustomUserIdentifiers(),","));
    }
      logger.debug("url: {}", urlBuilder.build().toString());

      Request request = client.getRequestBuilder()
          .get()
          .url(urlBuilder.build())
          .addHeader("range",header.get())
          .build();
      try {
          String responseBody = client.executeRequest(request).body().string();
          return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class,
              RankingItem.class));
      } catch (IOException | NullPointerException e) {
          throw new ToornamentException("Couldn't retrieve ranking items from tournament with id "+ tournamentID);
      }
  }
}
