package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Permission;
import com.toornament.model.enums.Attribute;
import com.toornament.model.enums.Scope;
import com.toornament.model.request.PermissionsQuery;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.LoggerFactory;

public class Permissions extends Concept {
  private String tournamentID;

  public Permissions(ToornamentClient client, String tournamentID) {
    super(client);
    this.tournamentID = tournamentID;
    logger = LoggerFactory.getLogger(this.getClass());
  }

  List<Permission> getPermissions() {
    Builder urlBuilder = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_PERMISSION)) {
      urlBuilder
          .scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("permissions");
    }

    logger.debug("url: {}", urlBuilder.build().toString());
    Request request = client.getAuthenticatedRequestBuilder().get().url(urlBuilder.build()).build();
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper
              .getTypeFactory()
              .constructCollectionType(List.class, Permission.class));
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new ToornamentException("Got IOException getting Permissions");
    }
  }

  Permission getPermission(String permissionID) {
    Builder url = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_PERMISSION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("permissions")
          .addEncodedPathSegment(permissionID);
    }

    Request request = client.getRequestBuilder().get().url(url.build()).build();
    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructType(com.toornament.model.Permission.class));
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new ToornamentException("Got IOException getting Permission.");
    }
  }

  Permission createPermission(PermissionsQuery query) {
    Builder url = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_PERMISSION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("permissions");
    }
    RequestBody body = RequestBody.create(ToornamentClient.JSON, query.toString());
    Request request = client.getRequestBuilder().post(body).url(url.build()).build();

    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructType(Permission.class));
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new ToornamentException("Error creating new permission");
    }
  }

  Permission updatePermissions(
      List<Attribute> attributes, String permissionID) {
    Builder url = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_PERMISSION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("permissions")
          .addEncodedPathSegment(permissionID);
    }

    String attributeString = "";
    try {
      attributeString =
          new ObjectMapper()
              .setSerializationInclusion(JsonInclude.Include.NON_NULL)
              .writeValueAsString(attributes);
    } catch (JsonProcessingException e) {
      throw new ToornamentException(
          "Couldn't create string from List of Attributes: " + e.getMessage());
    }

    RequestBody body = RequestBody.create(ToornamentClient.JSON, attributeString);
    Request request = client.getRequestBuilder().patch(body).url(url.build()).build();

    try {
      String responseBody = client.executeRequest(request).body().string();
      return mapper.readValue(
          responseBody,
          mapper.getTypeFactory().constructType(Permission.class));
    } catch (IOException | NullPointerException e) {
      logger.error(e.getMessage());
      throw new ToornamentException("Error updating new permission");
    }
  }

  Integer removePermission(String permissionsID) {
    Builder url = new Builder();
    if (client.getScope().contains(Scope.ORGANIZER_PERMISSION)) {
      url.scheme("https")
          .host("api.toornament.com")
          .addEncodedPathSegment("organizer")
          .addEncodedPathSegment("v2")
          .addEncodedPathSegment("tournaments")
          .addEncodedPathSegment(tournamentID)
          .addEncodedPathSegment("permissions")
          .addEncodedPathSegment(permissionsID);
    }
    Request request = client.getAuthenticatedRequestBuilder().delete().url(url.build()).build();
    return client.executeRequest(request).code();
  }
}
