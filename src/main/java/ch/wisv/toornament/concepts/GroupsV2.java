package ch.wisv.toornament.concepts;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.exception.ToornamentException;
import ch.wisv.toornament.model.Group;
import ch.wisv.toornament.model.request.GroupsQuery;
import ch.wisv.toornament.model.request.GroupsQuery.GroupsQueryBuilder;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class GroupsV2 extends Concept {
    private String tournamentID;
    public GroupsV2(ToornamentClient client, String tournamentID) {
        super(client);
        this.tournamentID = tournamentID;
    }
    public List<Group> getGroups(GroupsQuery parameters, Map<String,String> header){
        HttpUrl.Builder url = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addEncodedPathSegment("viewer")
            .addEncodedPathSegment("v2")
            .addEncodedPathSegment("tournaments")
            .addEncodedPathSegment(tournamentID)
            .addEncodedPathSegment("groups");

        if(!parameters.getStageIds().isEmpty())
            url.addQueryParameter("stage_ids", StringUtils.join(parameters.getStageIds(),","));
        if(!parameters.getStageNumbers().isEmpty())
            url.addQueryParameter("stage_numbers",StringUtils.join(parameters.getStageNumbers(),","));

        Request request = client.getRequestBuilder()
            .get()
            .url(url.build())
            .addHeader("range",header.get("range"))
            .build();
        try {
            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, Group.class));
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            throw new ToornamentException("Got IOExcption getting Groups");
        }
    }

    public Group getGroup(String groupID){
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
            .scheme("https")
            .host("api.toornament.com")
            .addPathSegment("viewer")
            .addPathSegment("v2")
            .addPathSegment("tournaments")
            .addPathSegment(tournamentID)
            .addPathSegment("groups")
            .addPathSegment(groupID);
        Request request = client.getRequestBuilder().get().url(urlBuilder.build()).build();
        try {

            String responseBody = client.executeRequest(request).body().string();
            return mapper.readValue(responseBody,mapper.getTypeFactory().constructType(Group.class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
