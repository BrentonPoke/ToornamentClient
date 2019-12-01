package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Permissions;
import ch.wisv.toornament.model.enums.Attribute;
import ch.wisv.toornament.model.enums.Scope;
import ch.wisv.toornament.model.request.PermissionsQuery;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PermissionsV2 extends Concept {
    private String tournamentID;
    public PermissionsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }


    List<Permissions> getPermissions(){
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
                mapper.getTypeFactory().constructCollectionType(List.class, Permissions.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Permissions");
        }
    }

    Permissions getPermission(String permissionID){
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
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructType(Permissions.class));
        } catch (IOException | NullPointerException e){
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOException getting Permission.");
        }
    }
    Permissions createPermission(PermissionsQuery query){
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
                mapper.getTypeFactory().constructType(Permissions.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Error creating new permission");
        }
    }

    Permissions updatePermissions(List<Attribute> attributes, String permissionID){
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
                mapper.getTypeFactory().constructType(Permissions.class));
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
        Request request = client.getRequestBuilder()
            .delete()
            .url(url.build())
            .build();
        return client.executeRequest(request).code();
    }
}
