package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Group;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.GroupsHeader;
import com.toornament.model.request.GroupsQuery;

import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

public class Groups extends Concept {
  private String tournamentID;

  public Groups(ToornamentClient client, String tournamentID) {
    super(client);
    this.tournamentID = tournamentID;
    logger = LoggerFactory.getLogger(this.getClass());
  }

  public List<Group> getGroups(GroupsQuery parameters, GroupsHeader header) {

    checkScope(Scope.ORGANIZER_RESULT);
    HttpUrl.Builder url =
        new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("organizer")
            .addPathSegment("v2")
            .addPathSegment("groups");

    if (!parameters.getStageIds().isEmpty())
      url.addQueryParameter("stage_ids", StringUtils.join(parameters.getStageIds(), ","));
    if (!parameters.getStageNumbers().isEmpty())
      url.addQueryParameter("stage_numbers", StringUtils.join(parameters.getStageNumbers(), ","));
    if (!parameters.getStageNumbers().isEmpty())
      url.addQueryParameter("tournament_ids", StringUtils.join(parameters.getStageNumbers(), ","));

    logger.debug("url: {}",url.build());

    Request request =
        client
            .getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range", header.get())
            .build();
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Group.class));
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new ToornamentException("Got IOException getting Groups");
    }
  }

  public Group getGroup(String groupID) {
    checkScope(Scope.ORGANIZER_RESULT);
    HttpUrl.Builder urlBuilder =
        new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("organizer")
            .addPathSegment("v2")
            .addPathSegment("groups")
            .addPathSegment(groupID);
    Request request = client.getRequestBuilder().get().url(urlBuilder.build()).build();
    try {

      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(Group.class));
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return null;
  }
}
