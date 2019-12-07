package com.toornament.concepts;

import com.toornament.ToornamentClient;
import com.toornament.exception.ToornamentException;
import com.toornament.model.Webhook;
import com.toornament.model.request.WebhookQuery;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WebhooksV2 extends Concept {
public WebhooksV2(ToornamentClient client){
    super(client);
}

public List<Webhook> getWebhooks(){
    HttpUrl.Builder url = new HttpUrl.Builder();
        url.scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("organizer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("webhooks");
    Request request = client.getAuthenticatedRequestBuilder()
        .get()
        .url(url.build())
        .build();
    try {
        String responseBody = client.executeRequest(request).body().string();
        return mapper.readValue(responseBody,
            mapper.getTypeFactory().constructCollectionType(List.class,Webhook.class));
    } catch (IOException | NullPointerException e) {
        System.out.println(e.getMessage());
        throw new ToornamentException("Got IOException getting webhooks");
    }
}

public Webhook createWebhook(WebhookQuery query){
    HttpUrl.Builder url = new HttpUrl.Builder();
    url.scheme("https")
        .host("api.toornament.com")
        .addEncodedPathSegment("organizer")
        .addEncodedPathSegment("v2")
        .addEncodedPathSegment("webhooks");
    RequestBody body = RequestBody.create(MediaType.parse("application/json"),query.toString());
    Request request = client.getAuthenticatedRequestBuilder()
        .post(body)
        .url(url.build())
        .build();

    try {
        String responseBody = client.executeRequest(request).body().string();
        return mapper.readValue(responseBody, Webhook.class);
    } catch (IOException e) {
        throw new ToornamentException("Couldn't create webhook: "+e.getMessage());
    }

}

}
