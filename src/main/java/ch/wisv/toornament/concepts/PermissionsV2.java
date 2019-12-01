package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Permissions;
import ch.wisv.toornament.model.enums.Scope;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;

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
}
