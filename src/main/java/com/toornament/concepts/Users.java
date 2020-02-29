package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.User;
import com.toornament.model.enums.Scope;
import java.io.IOException;
import okhttp3.HttpUrl.Builder;
import okhttp3.Request;

public class Users extends Concept {
    public Users(ToornamentClient client){
        super(client);
    }

    public User getUser(){
        Builder url = new Builder();
        if (client.getScope().contains(Scope.USER_INFO)) {
            url.scheme("https")
                .host("api.toornament.com")
                .addEncodedPathSegment("account")
                .addEncodedPathSegment("v2")
                .addEncodedPathSegment("me")
                .addEncodedPathSegment("info");
        }

        Request request = client.getAuthenticatedRequestBuilder()
            .get()
            .url(url.build())
            .build();

        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, User.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Couldn't get user");
        }
    }

}
