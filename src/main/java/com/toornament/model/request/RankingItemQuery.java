package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class RankingItemQuery {

    @JsonAlias("custom_user_identifiers")
    @Singular
    List<String> customUserIdentifiers;
    @JsonAlias("group_ids")
    @Singular
    List<String> groupIDs;
    @JsonAlias("group_numbers")
    @Singular
    List<String> groupNumbers;
    @JsonAlias("participant_ids")
    @Singular
    List<String> participantIDs;
}
