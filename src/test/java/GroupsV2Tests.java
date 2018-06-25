import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.GroupsV2;
import ch.wisv.toornament.model.Group;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GroupsV2Tests {
    private ToornamentClient client;
    private GroupsV2 groups;
    private Map<String,String> params =new HashMap<>();
    private Map<String,String> header = new HashMap<>();

    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"));
        client.authorize();
        groups = new GroupsV2(client,"906278647555784704");
        header.put("range","groups=0-49");
        params.put("stage_ids","906330006561030144,987313089934254080");
        params.put("stage_numbers","1");

    }
    @Test
    public void getGroupsV2Test(){
        List<Group> groupList = groups.getGroups(params,header);
        assertTrue(groupList.get(0).getNumber() == 1);
    }

    @Test
    public void getGroupTest(){
        Group group = groups.getGroup("986865420542550016");
        assertTrue(group.getNumber() == 1);
        assertTrue(group.getStage_id().matches("906330006561030144"));
    }
}
