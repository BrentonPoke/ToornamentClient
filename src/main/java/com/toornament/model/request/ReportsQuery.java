package com.toornament.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.Reports.Report;
import com.toornament.model.enums.ReportsType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportsQuery {
    String note;
    @JsonAlias("user_id")
    String userID;
    @JsonAlias("custom_user_identifier")
    String customUserIdentifier;
    Report report;
    ReportsType type;
    String participantID;
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
