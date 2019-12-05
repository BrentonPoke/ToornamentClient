package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reports {
    String note;
    String user_id;
    @JsonAlias("custom_user_identifier")
    String customUserIdentifier;
    @JsonAlias("participant_id")
    private String participantID;
    String type;
    private String id;
    @JsonAlias("closed_author_id")
    private String closedAuthorID;
    private Boolean closed;
    private LocalDateTime closed_at;
    Report report;
  public class Report {
    List<Opponent> opponents;
    }

    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
