package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class MatchDetails extends Match {

    private List<Stream> streams;
    private List<Vod> vods;
    @JsonProperty("private_note")
    private String privateNote;
    private String note;

}
