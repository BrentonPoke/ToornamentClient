package ch.wisv.toornament.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MatchDetails extends Match {

    private List<Stream> streams;
    private List<Vod> vods;
    @JsonProperty("private_note")
    private String privateNote;
    private String note;

}
