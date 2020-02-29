package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
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

public class Permissions extends Concept {
    private String tournamentID;
    public Permissions(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }


    List<com.toornament.model.Permissions> getPermissions(){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_PERMISSION)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions");
        }
        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructCollectionType(List.class, com.toornament.model.Permissions.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Permissions");
        }
    }

    com.toornament.model.Permissions getPermission(String permissionID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_PERMISSION)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions")
                .addEncodedPathSegment(permissionID);
        }

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .build();
        try{
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(
                com.toornament.model.Permissions.class));
        } catch (IOException | NullPointerException e){
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting Permission.");
        }
    }
    com.toornament.model.Permissions createPermission(PermissionsQuery query){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_PERMISSION)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
        Request request = client.getRequestBuilder()
            .post(body)
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(com.toornament.model.Permissions.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Error creating new permission");
        }
    }

    com.toornament.model.Permissions updatePermissions(List<Attribute> attributes, String permissionID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_PERMISSION)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions")
                .addEncodedPathSegment(permissionID);
        }

        String attributeString = "";
        try{
            attributeString= new ObjectMapper().setSerializationInclusion(
                JsonInclude.Include.NON_NULL).writeValueAsString(attributes);
        }catch (JsonProcessingException e){
            throw new ToornamentException("Couldn't create string from List of Attributes: " + e.getMessage());
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),attributeString);
        Request request = client.getRequestBuilder()
            .patch(body)
            .url(url.build())
            .build();

        try{
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,
                mapper.getTypeFactory().constructType(com.toornament.model.Permissions.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Error updating new permission");
        }
    }

    Integer removePermission(String permissionsID){
        Builder url = new Builder();
        if(client.getScope().contains(Scope.ORGANIZER_PERMISSION)){
            url
                .scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("organizer")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("tournaments")
                .addEncodedPathSegment(tournamentID)
                .addEncodedPathSegment("permissions")
                .addEncodedPathSegment(permissionsID);
        }
        Request request = client.getAuthenticatedRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }
}
