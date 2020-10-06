package com.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toornament.model.enums.Attribute;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class Permissions {
    private List<Attribute> attributes;
    private String email;
    private String id;
    @Override
    public String toString() {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
