import com.toornament.ToornamentClient;
import com.toornament.concepts.Groups;
import com.toornament.model.Group;
import com.toornament.model.enums.Scope;
import com.toornament.model.header.GroupsHeader;
import com.toornament.model.request.GroupsQuery;
import com.toornament.model.request.GroupsQuery.GroupsQueryBuilder;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GroupsTests {
    private ToornamentClient client;
    private Groups groups;
    private GroupsQueryBuilder params = GroupsQuery.builder();
    private GroupsHeader header = new GroupsHeader();
    private HashSet<Scope> scopes = new HashSet<>();
    @Before
    public void Setup() throws IOException {
        scopes.add(Scope.ORGANIZER_ADMIN);
        client = new ToornamentClient(System.getenv("KEY"), System.getenv("CLIENT"), System.getenv("SECRET"),
            scopes);
        groups = new Groups(client);
        header.build(0,49);
        params.stageId(906330006561030144L).stageId(987313089934254080L).stageNumber(1);

    }
    @Test
    public void getGroupsV2Test(){
        List<Group> groupList = groups.getGroups(params.build(),header);
        assertEquals(1, (int) groupList.get(0).getNumber());
    }

    @Test
    public void getGroupTest(){
        Group group = groups.getGroup("986865420542550016");
        assertEquals(1, (int) group.getNumber());
        assertTrue(group.getStageID().matches("906330006561030144"));
    }
}
